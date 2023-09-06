package persistence;

import org.example.persistence.UserRepository;
import org.example.models.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = UserRepository.getInstance();
    }

    @Test
    void testAddUser() {
        User user = new User("Alice", "Doe", "alice@email.com");
        String result = userRepository.addUser(user);
        assertEquals("The student has been added.", result);
    }

    @Test
    void testRemoveUser() {
        User user = new User("Bob", "Doe", "bob@email.com");
        userRepository.addUser(user);
        userRepository.removeUser(user);
        assertNull(userRepository.getUserById(user.getId()));
    }
}