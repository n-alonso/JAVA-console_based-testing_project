package models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.example.models.User;

class UserTest {

    @Test
    void testIdIncrement() {
        User user1 = new User("Alice", "Doe", "alice@email.com");
        User user2 = new User("Bob", "Doe", "bob@email.com");
        assertTrue(user2.getId() > user1.getId());
    }

    @Test
    void testUpdateJava() {
        User user = new User("Alice", "Doe", "alice@email.com");
        int updatedJavaScore = user.updateJava(5);
        assertEquals(5, updatedJavaScore);
    }
}