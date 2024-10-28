package com.cvmaker.cvmaker.validator;

import com.cvmaker.cvmaker.exception.InvalidFieldValueException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Link validator.
 */
public class LinkValidator {
    private final Pattern pattern;

    private final static  String LINK_REGEX =
            "^(https?://)?(www\\.)?[a-zA-Z0-9-]+(\\.[a-zA-Z]+)+(/[-a-zA-Z0-9@:%._+~#=]*)?$";

    /**
     * Instantiates a new Link validator.
     */
    public LinkValidator() {
        pattern = Pattern.compile(LINK_REGEX);
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
            throw new InvalidFieldValueException("Invalid url.");
        }
    }
}
