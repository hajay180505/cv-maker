package com.cvmaker.cvmaker.validator;

import java.time.LocalDate;
import java.time.Period;

/**
 * The type Date validator.
 */
public class DateValidator {
    /**
     * Is date before k years boolean.
     *
     * @param selectedDate the selected date
     * @param kYears       the k years
     * @return the boolean
     */
    public static boolean isDateBeforeKYears(LocalDate selectedDate, int kYears) {
        if (selectedDate == null) {
            return false; // No date selected
        }
        LocalDate today = LocalDate.now();
        LocalDate date18YearsAgo = today.minus(Period.ofYears(kYears));

        return selectedDate.isBefore(date18YearsAgo);
    }
}
