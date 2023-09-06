package services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.example.models.User;
import org.example.persistence.UserRepository;
import org.example.services.ConsoleInterface;

import java.util.List;


class ConsoleInterfaceTest {

    ConsoleInterface consoleInterface;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        consoleInterface = new ConsoleInterface(userRepository);
    }

    @ParameterizedTest(name = "Iteration {index} - User: {0}, equals when console parsed")
    @MethodSource("userInputsFactory")
    void testParseUser(String str) {
        String[] split = str.split(",");
        String userInput = str.replaceAll(",", " ");

        User actualUser = consoleInterface.parseUser(userInput);
        String expectedName = split[0];
        String expectedSurname = split[1];
        String expectedEmail = split[2];

        assertNotNull(actualUser);
        assertEquals(expectedName, actualUser.getName());
        assertEquals(expectedSurname, actualUser.getSurname());
        assertEquals(expectedEmail, actualUser.getEmail());
    }

    @Test
    void testParsePoints() {
        User user = new User("Alice", "Doe", "alice@email.com");
        int id = user.getId();

        when(userRepository.getUserById(id)).thenReturn(user);
        when(userRepository.updateUserById(any(User.class))).thenReturn(true);

        String result = consoleInterface.parsePoints(id + " 5 5 5 5");
        assertEquals("Points updated.", result);
    }

    static List<String> userInputsFactory() {
        return List.of(
                "a,b,a@b",
                "aa,bb,a@b",
                "Samuel,L Jackson,a@b",
                "William,Bradley Pitt,a@b"
        );
    }
}