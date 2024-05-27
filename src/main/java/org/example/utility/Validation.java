package org.example.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static final Pattern PASSWORD_PATTERN;
    public static final Pattern NATIONAL_CODE;
    static {
        PASSWORD_PATTERN=Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#!%&*])[A-Za-z0-9@#!%&*]{8,10}$");
        NATIONAL_CODE=Pattern.compile("^\\d{10}$");
    }
    public static boolean isValidPassword(String passWord){
        Matcher matcher=PASSWORD_PATTERN.matcher(passWord);
        return matcher.find();
    }
    public static boolean isValidNationalCode(String nationalCode){
        Matcher matcher=NATIONAL_CODE.matcher(nationalCode);
        return matcher.find();
    }

}
