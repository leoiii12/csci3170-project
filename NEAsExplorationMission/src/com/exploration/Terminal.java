package com.exploration;

import java.util.Scanner;

public class Terminal {

    public void display(String string) {
        System.out.print(string);
    }

    public void displayLine(String string) {
        System.out.println(string);
    }

    public void displayLine() {
        System.out.println();
    }

    public void displayError(String string) {
        System.err.println("[ERROR]: " + string);
        System.err.flush();
    }

    public int readInt() {
        Scanner scanner = new Scanner(System.in);

        int input;

        while (true) {
            try {
                input = scanner.nextInt();

                break;
            } catch (Exception e) {
                scanner.next();

                displayError("Please provide an integer.");
            }
        }

        return input;
    }

    public double readDouble() {
        Scanner scanner = new Scanner(System.in);

        int input;

        while (true) {
            try {
                input = scanner.nextInt();

                break;
            } catch (Exception e) {
                scanner.next();

                displayError("Please provide a double.");
            }
        }

        return input;
    }

    public String readString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

}
