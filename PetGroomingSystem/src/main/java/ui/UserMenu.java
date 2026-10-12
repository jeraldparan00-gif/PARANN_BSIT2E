package ui;

import dao.UserDao;
import model.User;

import java.util.List;
import java.util.Scanner;

/**
 * Screen shown after a successful login. Logout returns to the main menu.
 */
public class UserMenu {

    private final User user;
    private final Scanner scanner;
    private final UserDao userDao = new UserDao();

    public UserMenu(User user, Scanner scanner) {
        this.user = user;
        this.scanner = scanner;
    }

    public void show() {
        System.out.println("\nWelcome, " + user.username() + "!");
        System.out.println("Role: " + user.roles() + " | Status: " + user.status());
        if ("PENDING".equals(user.status())) {
            System.out.println("Note: your account is still PENDING approval.");
        }

        boolean isAdmin = "ADMIN".equals(user.roles());
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n--- " + user.roles() + " MENU ---");
            System.out.println("1. View Profile");
            if (isAdmin) {
                System.out.println("2. View All Users");
                System.out.println("3. Logout");
            } else {
                System.out.println("2. Logout");
            }
            System.out.print("Choose: ");

            String choice = scanner.nextLine().trim();
            if (choice.equals("1")) {
                viewProfile();
            } else if (isAdmin && choice.equals("2")) {
                viewAllUsers();
            } else if (choice.equals(isAdmin ? "3" : "2")) {
                System.out.println("Logged out successfully.");
                loggedIn = false;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void viewProfile() {
        System.out.println("ID: " + user.id());
        System.out.println("Username: " + user.username());
        System.out.println("Password: " + user.password());
        System.out.println("Role: " + user.roles());
        System.out.println("Status: " + user.status());
    }

    private void viewAllUsers() {
        List<User> users = userDao.findAll();
        System.out.println();
        System.out.printf("%-4s %-15s %-15s %-14s %-8s%n", "ID", "USERNAME", "PASSWORD", "ROLE", "STATUS");
        System.out.println("-".repeat(60));
        for (User u : users) {
            System.out.printf("%-4d %-15s %-15s %-14s %-8s%n",
                    u.id(), u.username(), u.password(), u.roles(), u.status());
        }
    }
}
