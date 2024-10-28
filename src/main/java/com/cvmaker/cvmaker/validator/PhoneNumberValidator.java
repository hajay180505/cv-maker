package com.cvmaker.cvmaker.validator;

import com.cvmaker.cvmaker.exception.InvalidFieldValueException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Phone number validator.
 */
public class PhoneNumberValidator {
    private final Pattern pattern;

    private static final String PHONE_NUMBER_PATTERN =
                "^\\+?\\d{1,3}?[-.\\s]?\\(?\\d{1,4}?\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}$";

    /**
     * Instantiates a new Phone number validator.
     */
    public PhoneNumberValidator() {
        pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
    }

    /**
     * Validate.
     *
     * @param hex the hex
     * @throws InvalidFieldValueException the invalid field value exception
     */
    public void validate(final String hex) throws InvalidFieldValueException {
        Matcher matcher = pattern.matcher(hex);
        if (!matcher.matches()) {
            throw new InvalidFieldValueException("Invalid phone number.");
        }
    }
}
