package ui;

import command.Command;
import command.CommandFactory;

import java.util.Scanner;

public class InputHandler {

    private CommandFactory commandFactory;
    private Scanner scanner;

    public InputHandler(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
        this.scanner = new Scanner(System.in);
    }
    public String processInput() {
        String input = scanner.nextLine();
        return input.trim();
    }
    public Command readCommand(String input){
        return commandFactory.create(input);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void setCommandFactory(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }
}