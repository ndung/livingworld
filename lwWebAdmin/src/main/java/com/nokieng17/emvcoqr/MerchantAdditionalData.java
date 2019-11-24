package com.nokieng17.emvcoqr;

import javax.validation.constraints.Size;

import com.nokieng17.emvcoqr.validation.EmvSpecification;
import com.nokieng17.emvcoqr.validation.RequireIso8859;

/***
 * @author magma17 The Additional Data Field Template includes information that
 *         may be provided by the Merchant or may be populated by the mobile
 *         application to enable or facilitate certain use cases.
 */
public class MerchantAdditionalData {

	@EmvSpecification(Id = 1, MaxLength = 25, message = "")
	@RequireIso8859
	public String billNumber;

	@EmvSpecification(Id = 2, MaxLength = 25)
	@RequireIso8859
	public String mobileNumber;

	@EmvSpecification(Id = 3, MaxLength = 25)
	@RequireIso8859
	public String storeLabel;

	@EmvSpecification(Id = 4, MaxLength = 25)
	@RequireIso8859
	public String loyaltyNumber;

	@EmvSpecification(Id = 5, MaxLength = 25)
	@RequireIso8859
	public String referenceLabel;

	@EmvSpecification(Id = 6, MaxLength = 25)
	@RequireIso8859
	public String customerLabel;

	@EmvSpecification(Id = 7, MaxLength = 25)
	@RequireIso8859
	public String terminalLabel;

	@EmvSpecification(Id = 8, MaxLength = 25)
	@RequireIso8859
	public String purposeOfTransaction;

	@EmvSpecification(Id = 9, MaxLength = 25)
	@RequireIso8859
	@Size(max = 3, message = "additionalConsumerDataRequest exceeds max length")
	public String additionalConsumerDataRequest;

	/***
	 * sets the bill number. The invoice number or bill number. This number could be
	 * provided by the merchant or could be an indication for the mobile application
	 * to prompt the consumer to input a Bill Number.
	 * 
	 * @param String
	 *                   billNumber
	 * @return MerchantAdditionalData
	 */

	public MerchantAdditionalData setBillNumber(String billNumber) {
		this.billNumber = billNumber;
		return this;
	}

	/***
	 * sets the mobile number. The mobile number could be provided by the merchant
	 * or could be an indication for the mobile application to prompt the consumer
	 * to input a Mobile Number.
	 * 
	 * @param String
	 *                   mobileNumber
	 * @return MerchantAdditionalData
	 */
	public MerchantAdditionalData setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
		return this;
	}

	/***
	 * sets the store label. A distinctive value associated to a store. This value
	 * could be provided by the merchant or could be an indication for the mobile
	 * application to prompt the consumer to input a Store Label.
	 * 
	 * @param String
	 *                   storeLabel
	 * @return MerchantAdditionalData
	 */
	public MerchantAdditionalData setStoreLabel(String storeLabel) {
		this.storeLabel = storeLabel;
		return this;
	}

	/***
	 * sets the loyalty number. Typically, a loyalty card number. This number could
	 * be provided by the merchant, if known, or could be an indication for the
	 * mobile application to prompt the consumer to input their Loyalty Number.
	 * 
	 * @param String
	 *                   loyaltyNumber
	 * @return MerchantAdditionalData
	 */
	public MerchantAdditionalData setLoyaltyNumber(String loyaltyNumber) {
		this.loyaltyNumber = loyaltyNumber;
		return this;
	}

	/***
	 * sets the reference label. Any value as defined by the merchant or acquire in
	 * order to identify the transaction. This value could be provided by the
	 * merchant or could be an indication for the mobile App to prompt the consumer
	 * to input a transaction Reference Label.
	 * 
	 * @param String
	 *                   referenceLabel
	 * @return MerchantAdditionalData
	 */
	public MerchantAdditionalData setReferenceLabel(String referenceLabel) {
		this.referenceLabel = referenceLabel;
		return this;
	}

	/***
	 * sets the customer label. Any value identifying a specific consumer. This
	 * value could be provided by the merchant (if known), or could be an indication
	 * for the mobile application to prompt the consumer to input their Customer
	 * Label.
	 * 
	 * @param String
	 *                   customerLabel
	 * @return MerchantAdditionalData
	 */
	public MerchantAdditionalData setCustomerLabel(String customerLabel) {
		this.customerLabel = customerLabel;
		return this;
	}

	/***
	 * sets the terminal label A distinctive value associated to a terminal in the
	 * store. This value could be provided by the merchant or could be an indication
	 * for the mobile application to prompt the consumer to input a Gets or sets the
	 * terminal label.
	 * 
	 * @param String
	 *                   terminalLabel
	 * @return MerchantAdditionalData
	 */
	public MerchantAdditionalData setTerminalLabel(String terminalLabel) {
		this.terminalLabel = terminalLabel;
		return this;
	}

	/***
	 * sets the purpose of transaction. Any value defining the purpose of the
	 * transaction. This value could be provided by the merchant or could be an
	 * indication for the mobile application to prompt the consumer to input a value
	 * describing the purpose of the transaction.
	 * 
	 * @param String
	 *                   purposeOfTransaction
	 * @return MerchantAdditionalData
	 */
	public MerchantAdditionalData setPurposeOfTransaction(String purposeOfTransaction) {
		this.purposeOfTransaction = purposeOfTransaction;
		return this;
	}

	/***
	 * sets the additional consumer data request. Contains indications that the
	 * mobile application is to provide the requested information in order to
	 * complete the transaction. The information requested should be provided by the
	 * mobile application in the authorization without unnecessarily prompting the
	 * consumer.
	 * 
	 * @param String
	 *                   additionalConsumerDataRequest
	 * @return MerchantAdditionalData
	 */
	public MerchantAdditionalData setAdditionalConsumerDataRequest(String additionalConsumerDataRequest) {
		this.additionalConsumerDataRequest = additionalConsumerDataRequest;
		return this;
	}

}
