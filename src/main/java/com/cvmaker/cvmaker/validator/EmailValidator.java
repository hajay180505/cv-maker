package com.cvmaker.cvmaker.validator;

import com.cvmaker.cvmaker.exception.InvalidFieldValueException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Email validator.
 */
public class EmailValidator {
    private final Pattern pattern;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Instantiates a new Email validator.
     */
    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
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
            throw new InvalidFieldValueException("Invalid email address.");
        }
    }


}
