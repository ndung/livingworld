package com.nokieng17.emvcoqr.validation;

import javax.validation.Constraint;


@Constraint(validatedBy = RequireIso8859Validator.class)
public @interface RequireIso8859 {

	String message() default "Invalid Charset";
}