package org.example.services;

import org.example.models.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private final Pattern englishLetters = Pattern.compile("^[a-zA-Z-']+$");
    private final Pattern length = Pattern.compile("[a-zA-Z-']{2}");
    private final Pattern noEndSpecialChars = Pattern.compile("(^-)|(-$)|(^')|('$)");
    private final Pattern noAdjacentSpecialChars = Pattern.compile("--|''|-'|'-");
    private final Pattern emailStructure = Pattern.compile("^[a-zA-Z0-9.'_-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)$");

    public String validate(User user) {
        if (!validateName(user.getName())) return "Incorrect first name.";
        for (String surname : user.getSurname().split(" ")) {
            if (!validateName(surname)) return "Incorrect last name.";
        }
        if (!validateEmail(user.getEmail())) return "Incorrect email.";

        return "valid";
    }

    public boolean validateName(String name) {
        if (!validateLength(name)) return false;
        if (!validateEnglishLetters(name)) return false;
        if (!validateSpecialChars(name)) return false;

        return true;
    }

    public boolean validateEmail(String email) {
        Matcher matcher = emailStructure.matcher(email);
        return matcher.matches();
    }

    public boolean validateLength(String word) {
        Matcher matcher = length.matcher(word);
        if (!matcher.find()) return false;

        return true;
    }

    public boolean validateEnglishLetters(String word) {
        Matcher matcher = englishLetters.matcher(word);
        if (!matcher.matches()) return false;

        return true;
    }

    public boolean validateSpecialChars(String word) {
        Matcher matcher1 = noEndSpecialChars.matcher(word);
        Matcher matcher2 = noAdjacentSpecialChars.matcher(word);
        if (matcher1.find()) return false;
        if (matcher2.find()) return false;

        return true;
    }
}
