package com.nokieng17.emvcoqr.iso;

public class IIso4217Currency {

	public String currency = null;
	public String alphabeticCode = null;
	public int numericCode = -1;

	public IIso4217Currency() {
	}

	public IIso4217Currency(String currency, String alphabeticCode, int numericCode) {
		this.currency = currency;
		this.alphabeticCode = alphabeticCode;
		this.numericCode = numericCode;
	}

	public String getCurrency() {
		return this.currency;
	}

	public String getAlphabeticCode() {
		return this.alphabeticCode;
	}

	public int getNumericCode() {
		return this.numericCode;
	}

}
