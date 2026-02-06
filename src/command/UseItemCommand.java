package command;

import model.Player;
import items.Item;
import ui.InputHandler;

public class UseItemCommand implements Command {

    private final Player player;
    private final InputHandler inputHandler;

    public UseItemCommand(Player player, InputHandler inputHandler) {
        this.player = player;
        this.inputHandler = inputHandler;
    }

    @Override
    public void execute() {
        if (player.getInventory().getItems().isEmpty()) {
            System.out.println("Žádné itemy v inventáři!");
            return;
        }
        System.out.println("Jaký předmět chcete využít?");
        player.getInventory().printContents();
        if (!inputHandler.getScanner().hasNextInt()) {
            System.out.println("Zadej číslo!");
            inputHandler.getScanner().next();
            return;
        }
        int index = inputHandler.getScanner().nextInt();
        Item item = player.getInventory().getItemByIndex(index);
        if (item == null) {
            System.out.println("Neplatný index!");
            return;
        }
        player.useItem(item, inputHandler.getScanner());
    }
}