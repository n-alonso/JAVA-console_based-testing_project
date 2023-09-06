package org.example;

import org.example.models.User;
import org.example.persistence.UserRepository;
import org.example.services.ConsoleInterface;
import org.example.services.UserValidator;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static UserValidator validator = new UserValidator();
    static UserRepository userRepository = UserRepository.getInstance();
    static ConsoleInterface console = new ConsoleInterface(userRepository);

    public static void main(String[] args) {
        console.printGreet();

        boolean iterate = true;
        while(iterate) {
            String input = scanner.nextLine();
            switch (input) {
                case "exit" -> iterate = false;
                case "add students" -> handleStudents();
                case "back" -> System.out.println("Enter 'exit' to exit the program");
                case "list" -> console.printStudentsIds();
                case "add points" -> handlePoints();
                case "find" -> handleFind();
                default -> {
                    Pattern noInput = Pattern.compile("^\\s*$");
                    Matcher matcher = noInput.matcher(input);
                    if (matcher.matches()) {
                        console.printNoInput();
                    } else {
                        console.printWrongInput();
                    }
                }
            }
        }

        console.printExit();
    }

    private static void handleStudents() {
        System.out.println("Enter student credentials or 'back' to return");
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("back")) {
                console.printStudentsSum();
                break;
            };

            User user = console.parseUser(userInput);
            if (user == null) {
                System.out.println("Incorrect credentials.");
                continue;
            }

            String validation = validator.validate(user);
            if (validation.equals("valid")) {
                String wasAdded = userRepository.addUser(user);
                System.out.println(wasAdded);
            } else {
                System.out.println(validation);
            }
        }
    }

    private static void handlePoints() {
        System.out.println("Enter an id and points or 'back' to return");
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("back")) {
                break;
            }

            String wasUserUpdated = console.parsePoints(userInput);
            System.out.println(wasUserUpdated);
        }
    }

    private static void handleFind() {
        System.out.println("Enter an id or 'back' to return");
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("back")) {
                break;
            }

            console.printStudentPoints(Integer.parseInt(userInput));
        }
    }
}
