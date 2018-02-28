package com.exploration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class OperationMenu implements Menu {

    protected final Terminal terminal;

    protected List<Operation> Operations = new ArrayList<>();

    OperationMenu(Terminal terminal) {
        this.terminal = terminal;
    }

    @Override
    public void print() {
        this.terminal.displayLine("What kinds of operation would you like to perform?");

        for (Operation operation : this.Operations) {
            this.terminal.displayLine(String.format("%d. %s", operation.getChoiceNumber(), operation.getDisplayString()));
        }
    }

    @Override
    public Menu nextMenu() {
        while (true) {
            this.terminal.display("Enter Your Choice: ");

            int choice = this.terminal.readInteger();
            Optional<Operation> operation = this.Operations.stream()
                    .filter(o -> o.getChoiceNumber() == choice)
                    .findFirst();

            if (operation.isPresent()) {
                try {
                    return operation.get().getCallable().call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected Menu getCurrentMenu() {
        return this;
    }
}
