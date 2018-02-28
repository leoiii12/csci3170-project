package com.exploration;

import java.util.concurrent.Callable;

public class Operation {

    private final String displayString;
    private final int choiceNumber;
    private final Callable<Menu> callable;

    Operation(String displayString, int choiceNumber, Callable<Menu> callable) {
        this.displayString = displayString;
        this.choiceNumber = choiceNumber;
        this.callable = callable;
    }

    public int getChoiceNumber() {
        return choiceNumber;
    }

    public String getDisplayString() {
        return displayString;
    }

    public Callable<Menu> getCallable() {
        return callable;
    }

}
