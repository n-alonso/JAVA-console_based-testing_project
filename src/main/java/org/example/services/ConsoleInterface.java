package org.example.services;

import org.example.models.User;
import org.example.persistence.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleInterface {

    UserRepository userRepository;
    private final String GREETING = "Learning Progress Tracker\n";
    private final String EXITING = "Bye!";
    private final String WRONG_INPUT = "\nUnknown command!\n";
    private final String NO_INPUT = "\nNo input\n";
    private final String STUDENT_NOT_FOUND = "No student is found for id=%s";

    public ConsoleInterface(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void printGreet() {
        System.out.println(GREETING);
    }

    public void printExit() {
        System.out.println(EXITING);
    }

    public void printWrongInput() {
        System.out.println(WRONG_INPUT);
    }

    public void printNoInput() {
        System.out.println(NO_INPUT);
    }

    public User parseUser(String userInput) {
        List<String> split = Arrays.stream(userInput.split(" ")).toList();
        if (split.size() < 3) {
            return null;
        }

        String name = split.get(0);
        String email = split.get(split.size() - 1);
        String surname = "";
        for (int index = 1; index < (split.size() - 1); index++) { // Leave out 1st and last element
            surname += split.get(index) + " ";
        }
        surname = surname.trim();

        return new User(name, surname, email);
    }

    public void printStudentsSum() {
        int count = userRepository.getUsersCount();
        System.out.println("\nTotal %d students have been added.".formatted(count));
    }

    public void printStudentsIds() {
        if (userRepository.getUsersCount() == 0) {
            System.out.println("No students found.");
            return;
        }

        List<User> users = userRepository.getUsers();
        System.out.println("Students:");
        users.stream().forEach(user -> {
            System.out.println(user.getId());
        });
    }

    public String parsePoints(String userInput) {
        String incorrect = "Incorrect points format.";
        String updated = "Points updated.";

        List<String> split = Arrays.stream(userInput.split(" ")).toList();
        if (split.size() != 5) return incorrect;

        String stringId = split.get(0);
        int userId;
        try {
            userId = Integer.parseInt(split.get(0));
        } catch (NumberFormatException e) {
            return STUDENT_NOT_FOUND.formatted(stringId);
        }

        User user = userRepository.getUserById(userId);
        if (user == null) return STUDENT_NOT_FOUND.formatted(stringId);

        // Skip the first element since its the user's id
        for (int index = 1; index < split.size(); index++) {
            int score;
            try {
                score = Integer.parseInt(split.get(index));
            } catch (NumberFormatException e) {
                return incorrect;
            }
            if (score < 0) return incorrect;

            switch (index) {
                case 1 -> user.updateJava(score);
                case 2 -> user.updateDSA(score);
                case 3 -> user.updateDatabases(score);
                case 4 -> user.updateSpring(score);
            }
        }

        boolean wasUserUpdated = userRepository.updateUserById(user);
        if (!wasUserUpdated) return STUDENT_NOT_FOUND.formatted(stringId);
        return updated;
    }

    public void printStudentPoints(int id) {
        User user = userRepository.getUserById(id);
        if (user == null) {
            System.out.println(STUDENT_NOT_FOUND.formatted(String.valueOf(id)));
            return;
        }

        System.out.println(user.scoresToString());
    }
}
