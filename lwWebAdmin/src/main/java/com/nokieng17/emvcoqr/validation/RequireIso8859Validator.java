package com.nokieng17.emvcoqr.validation;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RequireIso8859Validator implements ConstraintValidator<RequireIso8859, String> {

	public static final Charset Iso8859EncodingName = StandardCharsets.ISO_8859_1;

	@Override
	public boolean isValid(String str, ConstraintValidatorContext constraintContext) {
		if (null != str && str.trim().length() > 0) {
			byte[] bytes = str.getBytes(Iso8859EncodingName);
			String result = new String(bytes, Iso8859EncodingName);

			return str.equals(result);
		}
		return true;
	}
}