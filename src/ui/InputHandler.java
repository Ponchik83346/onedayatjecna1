package ui;

import command.Command;
import command.CommandFactory;

import java.util.Scanner;
/**
 * Zpracovává vstup hráče z konzole a převádí textový příkaz na odpovídající herní Command pomocí CommandFactory.
 */
public class InputHandler {

    private final CommandFactory commandFactory;
    private final Scanner scanner;

    public InputHandler(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
        this.scanner = new Scanner(System.in);
    }
    /**
     * Načte řádek vstupu od hráče.
     * Metoda blokuje vlákno do doby, než uživatel zadá text do standardního vstupu.
     * @return oříznutý textový vstup hráče
     */
    public String processInput() {
        return scanner.nextLine().trim();
    }
    /**
     * Převádí textový vstup hráče na herní příkaz.
     * @param input textový příkaz hráče
     * @return instance Command odpovídající vstupu nebo UnknownCommand
     */
    public Command readCommand(String input) {
        return commandFactory.create(input, this);
    }

    public Scanner getScanner() {
        return scanner;
    }
}