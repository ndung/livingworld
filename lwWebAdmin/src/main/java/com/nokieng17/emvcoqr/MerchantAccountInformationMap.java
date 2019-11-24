package com.nokieng17.emvcoqr;

import java.util.HashMap;

public class MerchantAccountInformationMap extends HashMap<Integer, MerchantAccountInformation> {

	/**
	 * HashMap serialVersionUID of MerchantAccountInformationMap
	 */
	private static final long serialVersionUID = -5759959864360899403L;

	public MerchantAccountInformationMap() {
		super();
	}

	public MerchantAccountInformationMap add(Integer key, MerchantAccountInformation value) {
		// TODO Auto-generated method stub
		super.put(key, value);
		return this;
	}

}
