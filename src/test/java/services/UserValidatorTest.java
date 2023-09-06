package services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.example.services.UserValidator;

import java.util.List;


class UserValidatorTest {

    UserValidator validator = new UserValidator();

    @Test
    @DisplayName("Name and each surname have at least 2 letters")
    void testValidateLength() {
        List<String> badNames = List.of("a", "b", "");
        List<String> goodNames = List.of("aa", "bb", "William", "Bradley", "Pitt");

        for (String name : badNames) {
            boolean actualBad = validator.validateLength(name);
            assertFalse(actualBad);
        }
        for (String name : goodNames) {
            boolean actualGood = validator.validateLength(name);
            assertTrue(actualGood);
        }
    }

    @Test
    @DisplayName("Name and each surname contain only [-'a-zA-z]")
    void testValidateEnglishLetters() {
        List<String> badNames = List.of("L.Jackson", "L$Jackson", "LJackson?", "LJackson!", "L&Jackson");
        List<String> goodNames = List.of("Samuel", "L-Jackson", "Connor", "O'Doherty");

        for (String name : badNames) {
            boolean actualBad = validator.validateEnglishLetters(name);
            assertFalse(actualBad);
        }
        for (String name : goodNames) {
            boolean actualGood = validator.validateEnglishLetters(name);
            assertTrue(actualGood);
        }
    }

    @Test
    @DisplayName("No 2 special characters together or at the beginning/end of a word")
    void testValidateSpecialChars() {
        List<String> badNames = List.of(
                "Tupac--Shakur", "Tupac''Shakur", "Tupac'-Shakur", "Tupac-'Shakur",
                "-Magic", "Johnson-", "-Magic-", "'Johnson", "Magic'", "'Johnson'"
        );

        List<String> goodNames = List.of("Samuel", "L-Jackson", "Connor", "O'Doherty");

        for (String name : badNames) {
            boolean actualBad = validator.validateSpecialChars(name);
            assertFalse(actualBad);
        }
        for (String name : goodNames) {
            boolean actualGood = validator.validateSpecialChars(name);
            assertTrue(actualGood);
        }
    }

    @Test
    @DisplayName("Email has appropriate structure (id@domain.domain)")
    void testValidateEmail() {
        List<String> badEmails = List.of("a@b", "a@b.", "a%b@something.com", "@something.com");
        List<String> goodEmails = List.of("a@b.com", "a@b.net", "connor-o'doherty@gmail.com");

        for (String email : badEmails) {
            boolean actualBad = validator.validateEmail(email);
            assertFalse(actualBad);
        }
        for (String email : goodEmails) {
            boolean actualGood = validator.validateEmail(email);
            assertTrue(actualGood);
        }
    }
}