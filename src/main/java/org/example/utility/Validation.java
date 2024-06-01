package org.example.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static final Pattern FIRSTNAME;
    public static final Pattern LASTNAME;
    public static final Pattern PASSWORD_PATTERN;
    public static final Pattern EMAIL_PATTERN;
    static {
        FIRSTNAME=Pattern.compile("");
        LASTNAME=Pattern.compile("");
        PASSWORD_PATTERN=Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#!%&*])[A-Za-z0-9@#!%&*]{8,10}$");
        EMAIL_PATTERN=Pattern.compile("^[a-zA-Z0-9].[a-zA-Z0-9\\._%\\+\\-]{0,63}@[a-zA-Z0-9\\.\\-]+\\.[a-zA-Z]{2,30}$");

    }
    public static boolean isValidFirstname(String firstname){
        Matcher matcher=FIRSTNAME.matcher(firstname);
        return matcher.find();
    }
    public static boolean isValidLastname(String lastname){
        Matcher matcher=LASTNAME.matcher(lastname);
        return matcher.find();
    }

    public static boolean isValidPassword(String passWord){
        Matcher matcher=PASSWORD_PATTERN.matcher(passWord);
        return matcher.find();
    }
    public static boolean isValidEmail(String email){
        Matcher matcher=EMAIL_PATTERN.matcher(email);
        return matcher.find();
    }

}
