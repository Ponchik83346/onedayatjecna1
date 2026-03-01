package command;

import model.Player;
import items.Item;
import ui.InputHandler;
/**
 * Příkaz pro použití předmětu z inventáře hráče.
 */
public class UseItemCommand implements Command {
    private final Player player;
    private final InputHandler inputHandler;
    /**
     * Vytvoří příkaz pro použití předmětu.
     * @param player hráč používající předmět
     * @param inputHandler vstup pro výběr předmětu
     */
    public UseItemCommand(Player player, InputHandler inputHandler) {
        this.player = player;
        this.inputHandler = inputHandler;
    }
    /**
     * Provede použití vybraného předmětu z inventáře.
     * Hráč musí zadat platný index předmětu.
     */
    @Override
    public void execute() {
        if (player.getInventory().getItems().isEmpty()) {
            System.out.println("Žádné itemy v inventáři!");
            return;
        }
        System.out.println("Jaký předmět chcete využít?");
        player.getInventory().printContents();
        System.out.print("Zadejte číslo: ");
        String input = inputHandler.getScanner().nextLine();
        int index;
        try {
            index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Neplatné číslo!");
            return;
        }
        Item item = player.getInventory().getItemByIndex(index);
        if (item == null) {
            System.out.println("Neplatný index!");
            return;
        }
        player.useItem(item, inputHandler);
    }
}