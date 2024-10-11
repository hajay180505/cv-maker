package schema;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class EmailValidator {
    private final Pattern pattern;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public void validate(final String hex) throws InvalidFieldValueException {
        Matcher matcher = pattern.matcher(hex);
        if (!matcher.matches()) {
            throw new InvalidFieldValueException("Invalid email address.");
        }
    }
}
