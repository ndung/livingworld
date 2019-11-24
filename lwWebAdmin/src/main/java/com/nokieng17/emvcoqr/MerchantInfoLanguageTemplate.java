package com.nokieng17.emvcoqr;

import javax.validation.constraints.NotNull;

import com.nokieng17.emvcoqr.validation.EmvSpecification;
import com.nokieng17.emvcoqr.validation.RequireIso8859;

public class MerchantInfoLanguageTemplate {

	@EmvSpecification(Id = 0, MaxLength = 2)
	@RequireIso8859
	@NotNull(message = "LanguagePreference is null")
	public String languagePreference;

	@EmvSpecification(Id = 1, MaxLength = 25)
	@RequireIso8859
	@NotNull(message = "merchantNameAlternateLanguage is null")
	public String merchantNameAlternateLanguage;

	@EmvSpecification(Id = 2, MaxLength = 15)
	@RequireIso8859
	public String merchantCityAlternateLanguage;

	public MerchantInfoLanguageTemplate setLanguagePreference(String languagePreference) {
		this.languagePreference = languagePreference;
		return this;
	}

	public MerchantInfoLanguageTemplate setMerchantNameAlternateLanguage(String merchantNameAlternateLanguage) {
		this.merchantNameAlternateLanguage = merchantNameAlternateLanguage;
		return this;
	}

	public MerchantInfoLanguageTemplate setMerchantCityAlternateLanguage(String merchantCityAlternateLanguage) {
		this.merchantCityAlternateLanguage = merchantCityAlternateLanguage;
		return this;
	}

}
