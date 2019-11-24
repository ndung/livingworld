package com.nokieng17.emvcoqr;

import java.util.HashMap;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.nokieng17.emvcoqr.validation.EmvSpecification;
import com.nokieng17.emvcoqr.validation.RequireIso8859;

public class MerchantUnreservedTemplate {

	@EmvSpecification(Id = 0, MaxLength = 32)
	@RequireIso8859
	@NotNull(message = "globalUniqueIdentifier is null")
	@Valid
	public String globalUniqueIdentifier;

	public ContextSpecificData contextSpecificData;

	/***
	 * An identifier that sets the context of the data that follows. The value is
	 * one of the following:
	 * <ul>
	 * <li>an Application Identifier (AID)</li>
	 * <li>a [UUID] without the hyphen (-) separators</li>
	 * <li>a reverse domain name</li>
	 * </ul>
	 * 
	 * @param String
	 *                   globalUniqueIdentifier
	 * @return MerchantPayload
	 */
	public MerchantUnreservedTemplate setGlobalUniqueIdentifier(String globalUniqueIdentifier) {
		this.globalUniqueIdentifier = globalUniqueIdentifier;
		return this;
	}

	/***
	 * Association of data objects to IDs and type of data object is specific to the
	 * Globally Unique Identifier. Identifiers must be between <b>1</b> and
	 * <b>99</b>.
	 * 
	 * @param        int
	 *                   key Tag ID
	 * @param String
	 *                   contextSpecificData
	 * @return MerchantPayload
	 */
	public MerchantUnreservedTemplate setContextSpecificData(int key, String contextSpecificData) {
		if (null == this.contextSpecificData) {
			this.contextSpecificData = new ContextSpecificData();
		}
		this.contextSpecificData.put(key, contextSpecificData);
		return this;
	}

	public class ContextSpecificData extends HashMap<Integer, String> {

		private static final long serialVersionUID = 69517450821999782L;

		public ContextSpecificData() {
			super();
		}

	}
}
