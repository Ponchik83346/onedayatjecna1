package command;

import items.Item;
import model.Game;
import model.GameState;
import model.Player;
import ui.InputHandler;

public class CommandFactory {
    private final Player player;
    private final InputHandler inputHandler;
    private final Game game;

    public CommandFactory(Player player, InputHandler inputHandler, Game game) {
        this.player = player;
        this.inputHandler = inputHandler;
        this.game = game;
    }

    public Command create(String input) {
        input = input.toLowerCase().trim();

        switch (input) {
            case "left":
                return player::moveLeft;
            case "right":
                return player::moveRight;
            case "enter":
                return player::enterRoom;
            case "exit room":
                return player::exitRoom;
            case "inventory":
                return () -> player.openInventory(player.getInventory());
            case "use":
                if (player.getInventory().getItems().isEmpty()) {
                    System.out.println("Žádné itemy v inventáři!");
                    return () -> {};
                }
                System.out.println("Jaký předmět chete využít?");
                player.getInventory().printContents();
                int itemIndex = inputHandler.getScanner().nextInt();
                Item item = player.getInventory().getItemByIndex(itemIndex);
                return () -> player.useItem(item, inputHandler.getScanner());
            case "help":
                showHelp();
            case "exit game":
                return new ExitCommand(game);
            default:
                System.out.println("Neznámý příkaz: " + input);
                return () -> {};
        }
    }
    private void showHelp() {
        System.out.println("=== HELP ===");
        System.out.println("left - jdi doleva");
        System.out.println("right - jdi doprava");
        System.out.println("enter - jdi do mistnosti");
        System.out.println("exit - jdi ven z mistnosti");
        System.out.println("inventory - otevrit inventar");
        System.out.println("use - využít předmět");
        System.out.println("help - show this help");
        System.out.println("quit / exitgame - exit the game");
    }
}