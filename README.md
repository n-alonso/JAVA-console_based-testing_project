# Learning Management System Console App (Java)

A console-based LMS application implemented in Java for the purpose of practicing Java programming, testing with JUnit and Mockito, and the implementation of various design patterns and collections.

## Table of Contents

- [Purpose](#purpose)
- [Features](#features)
  - [Design Patterns](#design-patterns)
  - [Collections](#collections)
  - [Equality](#equality)
- [Models](#models)
- [Services](#services)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)

## Purpose

The primary objective is to:

- Practice Java programming.
- Gain hands-on experience with unit testing.
- Apply various design patterns and choose appriate collections, like Singleton, Template, Maps for constant O(1) lookup time complexity, etc.
- Apply best practices for `equals()` and `hashCode()` methods.

## Features

### Design Patterns

- **Singleton**: Ensures that only a single instance of `UserRepository` is created. This is primarily used to maintain a single User Collection throughout the application's lifetime.
  
- **Template**: Template is used with `UserValidator` service. This allows to provide high abstraction from it's implementation details in other parts of the code, which will make it easier to change validation rules in the future.

### Collections

- **Double Maps for Users**: Granted that decission is to not use a DataBase in this project and thus practice collections.
  2 maps where chosen to ensure uniqueness of users both by Id and Email instead of Sets to make it a constant O(1) lookup time and mimic operations `byId()` that are typical from DataBases (Thus making it easier to replace them by a DB in the future).

### Equality

- `equals()` method is `@Override`d in `User` class to ensure proper functionality with collections. Implementation is done following best practices to ensure formal rules are complied with.
- `hashCode()` method is also `@Override`n for the same reasons. Implementation follows best practices described by _Joshua Block_ in _Effective Java_ (31 * result + code).

## Models

- **User**:
  - Fields include `id`, `name`, `surname`, `email`, `scores`.
  - Methods include getters & setters, `updateXXX()` for each score, `equals()`, `hashCode()`, `toString()`, `scoresToString()`.

## Services

- **ConsoleInterface**: Manages all user interactions through a console-based UI.
- **UserValidator**: Uses regex & pattern matching to validate user input when adding new students.

## Getting Started

### Prerequisites

- Java JDK 8 or above
- JUnit
- Mockito

### Installation

1. **Clone the Repo**
    ```bash
    git clone https://github.com/n-alonso/JAVA-console_based_testing_project.git
    ```
    
2. **Navigate to Project Directory**
    ```bash
    cd JAVA-console_based_testing_project
    ```
    
3. **Compile Java Files**
    ```bash
    javac -d out src/*.java
    ```

4. **Run the Main Class**
    ```bash
    java -cp out Main
    ```

## Usage

Run the application and follow the on-screen instructions to add students, list or find them, and track their points for the offered learning tracks.
