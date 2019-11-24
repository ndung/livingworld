package com.nokieng17.emvcoqr.encoding;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Map.Entry;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import com.nokieng17.crccalc.Crc16;
import com.nokieng17.crccalc.CrcCalculator;
import com.nokieng17.emvcoqr.MerchantAccountInformation;
import com.nokieng17.emvcoqr.MerchantAccountInformationMap;
import com.nokieng17.emvcoqr.MerchantAdditionalData;
import com.nokieng17.emvcoqr.MerchantInfoLanguageTemplate;
import com.nokieng17.emvcoqr.MerchantPayload;
import com.nokieng17.emvcoqr.MerchantUnreservedMap;
import com.nokieng17.emvcoqr.MerchantUnreservedTemplate;
import com.nokieng17.emvcoqr.MerchantUnreservedTemplate.ContextSpecificData;
import com.nokieng17.emvcoqr.MerchantAccountInformation.PaymentNetworkSpecifics;
import com.nokieng17.emvcoqr.validation.EmvSpecification;

public class PayloadEncoding implements IPayloadEncoding<MerchantPayload> {

	private static final Validator validator = Validation.byDefaultProvider().configure()
			.messageInterpolator(new ParameterMessageInterpolator()).buildValidatorFactory().getValidator();

	@Override
	public String GeneratePayload(MerchantPayload instance) throws IllegalArgumentException, IllegalAccessException {

		this.validate(instance);
		String payloadWithCrc = this.generatePayloadWithCRC(instance);

		return payloadWithCrc;
	}

	private void validate(MerchantPayload instance) throws RuntimeException {

		validObjectConstrain(instance);
		if (instance.merchantAccountInformations.size() > 0) {
			for (Entry<Integer, MerchantAccountInformation> acc : instance.merchantAccountInformations.entrySet()) {
				validObjectConstrain(acc.getValue());
				if (acc.getKey() < 2 && acc.getKey() > 51) {
					throw new RuntimeException("Merchant Account Information tag id out of range [2~51]");
				}
				for (Entry<Integer, String> item : acc.getValue().paymentNetworkSpecifics.entrySet()) {
					if (item.getKey() < 1 && item.getKey() > 99) {
						throw new RuntimeException(
								"Merchant Account Information tag paymentNetworkSpecifics out of range [1~99]");
					}
				}
			}
		} else {
			throw new RuntimeException("There must be at least one Merchant Account Information tag");
		}

		if (null != instance.additionalData) {
			validObjectConstrain(instance.additionalData);
		}
		if (null != instance.merchantLanguageInformation) {
			validObjectConstrain(instance.merchantLanguageInformation);
		}

		if (null != instance.unreservedTemplate && instance.unreservedTemplate.size() > 0) {
			for (Entry<Integer, MerchantUnreservedTemplate> unreserved : instance.unreservedTemplate.entrySet()) {
				validObjectConstrain(unreserved.getValue());
				if (unreserved.getKey() < 50 && unreserved.getKey() > 99) {
					throw new RuntimeException("unreservedTemplate tag id out of range [50~99]");
				}
				for (Entry<Integer, String> item : unreserved.getValue().contextSpecificData.entrySet()) {
					if (item.getKey() < 1 && item.getKey() > 99) {
						throw new RuntimeException(
								"unreservedTemplate tag contextSpecificData out of range [1~99]");
					}
				}
			}
		}
	}

	private <T> void validObjectConstrain(T object) {

		Set<ConstraintViolation<T>> constrains = validator.validate(object);

		for (ConstraintViolation<T> c : constrains) {
			System.out.println(c.getMessage());
		}
		if (constrains.size() > 0) {
			throw new RuntimeException(constrains.iterator().next().getMessage());
		}
	}

	private String generatePayloadWithCRC(MerchantPayload instance)
			throws IllegalArgumentException, IllegalAccessException {

		String payload = this.generatePayloadWithCrcID(instance);
		payload += "6304";
		byte[] payloadByte = payload.getBytes(StandardCharsets.UTF_8);

		CrcCalculator crc16CcittFalseCalc = new CrcCalculator(Crc16.Crc16CcittFalse);
		String crc = Long.toHexString(crc16CcittFalseCalc.Calc(payloadByte, 0, payloadByte.length)).toUpperCase();
		crc = crc.length() == 3 ? "0" + crc : crc;

		return String.format("%s%s", payload, crc);
	}

	private String generatePayloadWithCrcID(MerchantPayload instance)
			throws IllegalArgumentException, IllegalAccessException {

		return this.buildObjectFileds(instance).toString();
	}

	private <T> StringBuilder buildObjectFileds(T object) throws IllegalArgumentException, IllegalAccessException {
		StringBuilder qrBuilder = new StringBuilder();
		Field[] fields = object.getClass().getFields();
		for (Field field : fields) {
			String propName = field.getName();
			Object propValue = field.get(object);

			if (propValue instanceof MerchantAccountInformationMap) {
				MerchantAccountInformationMap accounts = (MerchantAccountInformationMap) propValue;
				for (Entry<Integer, MerchantAccountInformation> item : accounts.entrySet()) {
					String acc = this.buildObjectFileds(item.getValue()).toString();
					acc += this.buildObjectFileds(item.getValue().paymentNetworkSpecifics).toString();

					qrBuilder.append(String.format("%02d%02d%s", item.getKey(), acc.length(), acc));
				}

			} else if (propValue instanceof MerchantAdditionalData) {
				MerchantAdditionalData addition = (MerchantAdditionalData) propValue;
				String add = this.buildObjectFileds(addition).toString();

				qrBuilder.append(String.format("%02d%02d%s", 62, add.length(), add));

			} else if (propValue instanceof PaymentNetworkSpecifics) {
				PaymentNetworkSpecifics specifict = (PaymentNetworkSpecifics) propValue;
				for (Entry<Integer, String> item : specifict.entrySet()) {
//					System.out.println(
//							String.format("%02d%02d%s", item.getKey(), item.getValue().length(), item.getValue()));
					qrBuilder.append(
							String.format("%02d%02d%s", item.getKey(), item.getValue().length(), item.getValue()));
				}

			} else if (propValue instanceof MerchantInfoLanguageTemplate) {
				MerchantInfoLanguageTemplate lan = (MerchantInfoLanguageTemplate) propValue;
				String add = this.buildObjectFileds(lan).toString();

				qrBuilder.append(String.format("%02d%02d%s", 64, add.length(), add));

			} else if (propValue instanceof MerchantUnreservedMap) {
				MerchantUnreservedMap maps = (MerchantUnreservedMap) propValue;
				for (Entry<Integer, MerchantUnreservedTemplate> item : maps.entrySet()) {
					String unreserved = this.buildObjectFileds(item.getValue()).toString();
					unreserved += this.buildObjectFileds(item.getValue().contextSpecificData).toString();

					qrBuilder.append(String.format("%02d%02d%s", item.getKey(), unreserved.length(), unreserved));
				}

			} else if (propValue instanceof ContextSpecificData) {
				ContextSpecificData specifict = (ContextSpecificData) propValue;
				for (Entry<Integer, String> item : specifict.entrySet()) {
//					System.out.println(
//							String.format("%02d%02d%s", item.getKey(), item.getValue().length(), item.getValue()));
					qrBuilder.append(
							String.format("%02d%02d%s", item.getKey(), item.getValue().length(), item.getValue()));
				}

			} else {
				String tag = this.encodeProperty(object, propName, propValue);
//				System.out.println(String.format("%20s %s %s", propName, propValue, tag));
				if (null != tag && tag.length() > 0) {
					qrBuilder.append(tag);
				}
			}
		}
		return qrBuilder;

	}

	private <T, V> String encodeProperty(T instance, String propName, V propValue) {
		EmvSpecification emv = this.getAnnotationProperties(instance, propName);
		if (null != emv) {
			String id = String.format("%02d", emv.Id());
			String value = this.encodePropertyValue(propValue);
			String length = String.format("%02d", value.length());
			if (value.length() <= 0) {
				return "";
			}
			return String.format("%s%s%s", id, length, value);
		}
		return "";
	}

	private <T> EmvSpecification getAnnotationProperties(T instance, String name) {
		try {
			return instance.getClass()
					.getField(name)
					.getAnnotation(EmvSpecification.class);
		} catch (NoSuchFieldException | SecurityException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	private <T> String encodePropertyValue(T propertyValue) {
		if (propertyValue instanceof Integer && (Integer) propertyValue > -1) {
			return String.format("%02d", propertyValue);
		} else if (propertyValue instanceof Double && (Double) propertyValue > 0d) {
			return String.format("%.2f", propertyValue).replace(".00", "");
		} else if (propertyValue instanceof String) {
			return String.format("%s", propertyValue);
		} else {
			return "";
		}
	}

}
