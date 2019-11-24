package com.nokieng17.emvcoqr;

import java.util.HashMap;

import javax.validation.constraints.NotNull;

import com.nokieng17.emvcoqr.validation.EmvSpecification;

public class MerchantAccountInformation {

	@NotNull(message = "globalUniqueIdentifier is null")
	@EmvSpecification(Id = 0, MaxLength = 32, message = "length of globalUniqueIdentifier is exceed MaxLength")
	public String globalUniqueIdentifier;

	public PaymentNetworkSpecifics paymentNetworkSpecifics;

	public MerchantAccountInformation() {
		paymentNetworkSpecifics = new PaymentNetworkSpecifics();
	}

	public String getGlobalUniqueIdentifier() {
		return globalUniqueIdentifier;
	}

	public MerchantAccountInformation setGlobalUniqueIdentifier(String globalUniqueIdentifier) {
		this.globalUniqueIdentifier = globalUniqueIdentifier;
		return this;
	}

	public HashMap<Integer, String> getPaymentNetworkSpecifics() {
		return paymentNetworkSpecifics;
	}

	public MerchantAccountInformation addPaymentNetworkSpecifics(int key, String value) {
		this.validate(key, value);
		this.paymentNetworkSpecifics.put(key, value);
		return this;
	}

	private void validate(int key, String value) {
		if (key < 1 || key > 99) {
			throw new IllegalArgumentException(
					String.format("%s %02d", "an paymentNetworkSpecific ID was in invalid ID range: ID", key)
			);
		}
		if (this.getPaymentNetworkSpecifics().containsKey(key)) {
			throw new IllegalArgumentException(
					String.format("%s %02d", "an paymentNetworkSpecific ID was duplicated: ID", key)
			);
		}
	}

	public class PaymentNetworkSpecifics extends HashMap<Integer, String> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 7698382395951913113L;

		public PaymentNetworkSpecifics() {
			super();
		}

	}
}
