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

    public int readInteger() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

}
