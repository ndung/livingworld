package com.nokieng17.emvcoqr;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.nokieng17.emvcoqr.validation.EmvSpecification;
import com.nokieng17.emvcoqr.validation.RequireIso8859;

public class MerchantPayload {

	@EmvSpecification(Id = 0, MaxLength = 2)
	@RequireIso8859
	@NotNull(message = "payloadFormatIndicator is null")
	public int payloadFormatIndicator = -1;

	@EmvSpecification(Id = 1, MaxLength = 2)
	@RequireIso8859
	@Min(value = 11, message = "MerchantAccountInformations is out of range [11~12]")
	@Max(value = 12, message = "MerchantAccountInformations is out of range [11~12]")
	public int pointOfInitializationMethod = -1;

	@NotNull(message = "merchantAccountInformations is null")
	public MerchantAccountInformationMap merchantAccountInformations;

	@EmvSpecification(Id = 52, MaxLength = 4)
	@RequireIso8859
	@NotNull(message = "MerchantCategoryCode is null")
	@Min(value = -1, message = "merchantCategoryCode is out of range [1~9999]")
	@Max(value = 9999, message = "merchantCategoryCode is out of range [1~9999]")
	public int merchantCategoryCode = -1;

	@EmvSpecification(Id = 58, MaxLength = 2)
	@RequireIso8859
	@NotNull(message = "CountyCode is null")
	public String countyCode;

	@EmvSpecification(Id = 59, MaxLength = 25)
	@RequireIso8859
	@NotNull(message = "MerchantName is null")
	public String merchantName;

	@EmvSpecification(Id = 60, MaxLength = 15)
	@RequireIso8859
	@NotNull(message = "MerchantCity is null")
	public String merchantCity;

	@EmvSpecification(Id = 61, MaxLength = 10)
	@RequireIso8859
	public String postalCode;

	// [EmvSpecification(64, IsParent = true)]
	// [ValidateObject]
	public MerchantInfoLanguageTemplate merchantLanguageInformation;

	@EmvSpecification(Id = 54, MaxLength = 13)
	@RequireIso8859
	@Min(value = 0, message = "transactionAmount is less than zero")
	public double transactionAmount = 0d;

	@EmvSpecification(Id = 53, MaxLength = 3)
	@RequireIso8859
	@Min(value = -1, message = "transactionCurrency is less than zero")
	public int transactionCurrency = -1;

	@EmvSpecification(Id = 55, MaxLength = 2)
	@RequireIso8859
	@Min(value = -1, message = "TipOrConvenienceIndicator is out of range [1~3]")
	@Max(value = 3, message = "TipOrConvenienceIndicator is out of range [1~3]")
	public int tipOrConvenienceIndicator = -1;

	@EmvSpecification(Id = 56, MaxLength = 13)
	@RequireIso8859
	public String valueOfConvenienceFeeFixed;

	public String valueOfConvenienceFeePercentage;

	// [EmvSpecification(62, IsParent = true)]
	// [ValidateObject]
	public MerchantAdditionalData additionalData;

	// [EmvSpecification(91, IsParent = true)]
	// [ValidateObject]
	public MerchantUnreservedMap unreservedTemplate;

	public MerchantPayload setPayloadFormatIndicator(int payloadFormatIndicator) {
		this.payloadFormatIndicator = payloadFormatIndicator;
		return this;
	}

	public MerchantPayload setPointOfInitializationMethod(int pointOfInitializationMethod) {
		this.pointOfInitializationMethod = pointOfInitializationMethod;
		return this;
	}

	public MerchantPayload addMerchantAccountInformation(int id,
			MerchantAccountInformation merchantAccountInformation) {
		if (null == this.merchantAccountInformations) {
			this.merchantAccountInformations = new MerchantAccountInformationMap();
		}
		this.merchantAccountInformations.put(id, merchantAccountInformation);
		return this;
	}

	public MerchantPayload setMerchantAccountInformation(MerchantAccountInformationMap accounts) {
		if (null == this.merchantAccountInformations) {
			this.merchantAccountInformations = new MerchantAccountInformationMap();
		} else {
			this.merchantAccountInformations.clear();
		}
		this.merchantAccountInformations.putAll(accounts);
		accounts.clear();
		return this;
	}

	public MerchantPayload setMerchantCategoryCode(int merchantCategoryCode) {
		this.merchantCategoryCode = merchantCategoryCode;
		return this;
	}

	public MerchantPayload setTransactionCurrency(int transactionCurrency) {
		this.transactionCurrency = transactionCurrency;
		return this;
	}

	public MerchantPayload setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
		return this;
	}

	public MerchantPayload setTipOrConvenienceIndicator(int tipOrConvenienceIndicator) {
		this.tipOrConvenienceIndicator = tipOrConvenienceIndicator;
		return this;
	}

	public MerchantPayload setValueOfConvenienceFeeFixed(String valueOfConvenienceFeeFixed) {
		this.valueOfConvenienceFeeFixed = valueOfConvenienceFeeFixed;
		return this;
	}
	public MerchantPayload setValueOfConvenienceFeePercentage(String valueOfConvenienceFeePercentage) {
		this.valueOfConvenienceFeePercentage = valueOfConvenienceFeePercentage;
		return this;
	}

	public MerchantPayload setCountyCode(String countyCode) {
		this.countyCode = countyCode;
		return this;
	}

	public MerchantPayload setMerchantName(String merchantName) {
		this.merchantName = merchantName;
		return this;
	}

	public MerchantPayload setMerchantCity(String merchantCity) {
		this.merchantCity = merchantCity;
		return this;
	}

	public MerchantPayload setPostalCode(String postalCode) {
		this.postalCode = postalCode;
		return this;
	}

	public MerchantPayload setAdditionalData(MerchantAdditionalData additionalData) {
		this.additionalData = additionalData;
		return this;
	}

	public MerchantPayload setMerchantLanguageInformation(MerchantInfoLanguageTemplate merchantInformation) {
		this.merchantLanguageInformation = merchantInformation;
		return this;
	}

	public MerchantPayload setUnreservedTemplate(int id, MerchantUnreservedTemplate unreservedTemplate) {
		if (null == this.unreservedTemplate) {
			this.unreservedTemplate = new MerchantUnreservedMap();
		}
		this.unreservedTemplate.put(id, unreservedTemplate);
		return this;
	}

}
