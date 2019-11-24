package com.nokieng17.emvcoqr.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmvSpecificationValidator implements ConstraintValidator<EmvSpecification, Object> {

	private int maxLength = 99;
	private int id;

	@Override
	public void initialize(EmvSpecification constraintAnnotation) {

		this.maxLength = constraintAnnotation.MaxLength();
		this.id = constraintAnnotation.Id();
	}

	@Override
	public boolean isValid(Object tag, ConstraintValidatorContext context) {

		if (this.id < 0 || this.id > 99) {
			return overideDefaultMessage(context,
					String.format("Tag ID of data %s is out of range [0~99], actual is %02d", tag, this.id));
		}
		if (null != tag) {
			String value = null;
			if (tag instanceof String) {
				value = (String) tag;
			} else if (tag instanceof Integer) {
				value = String.valueOf((int) tag);
			} else if (tag instanceof Double) {
				value = String.valueOf((Double) tag);
			} else {
				throw new IllegalArgumentException("Unsupported data type: " + tag.toString());
			}
			if (value.length() > this.maxLength) {
				return overideDefaultMessage(context,
						String.format("value %s exceed max length %s", value, this.maxLength));
			}
		}
		return true;
	}

	private boolean overideDefaultMessage(ConstraintValidatorContext context, String message) {
		context.disableDefaultConstraintViolation();
		// build new violation message and add it
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
		return false;
	}

}
