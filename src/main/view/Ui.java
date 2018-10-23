package view;

import java.util.Scanner;

public class Ui {

    private Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    public String readUserCommand() {
        printQuery();
        return in.nextLine().trim();
    }

    public void printError(String msg) {
        System.out.println(msg);
        printQuery();
    }

    public void printError() {
        System.out.println("Unknown command! please try again");
    }

    public void printWelcome() {
        System.out.println("Welcome to PharmHana!");
        System.out.println("Your friendly medicine management app :)");
    }

    public void showToUser(String msg) {
        System.out.println(msg);
    }

    public void printQuery() {
        System.out.print("Your task? ");
    }
}
