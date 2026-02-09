package command;

import items.Item;
import map.Room;
import model.Player;
import ui.InputHandler;

public class DropItemCommand implements Command {

    private Player player;
    private InputHandler input;

    public DropItemCommand(Player player, InputHandler input) {
        this.player = player;
        this.input = input;
    }
    @Override
    public void execute() {
        if (!player.isInsideRoom()) {
            System.out.println("Nejsi v místnosti.");
            return;
        }
        if (player.getInventory().getItems().isEmpty()) {
            System.out.println("Inventář je prázdný.");
            return;
        }
        System.out.println("Inventář:");
        player.getInventory().printContents();
        System.out.print("Vyber index itemu na zahození: ");
        int index;
        try {
            index = Integer.parseInt(input.getScanner().nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Neplatný vstup.");
            return;
        }
        if (index < 0 || index >= player.getInventory().getItems().size()) {
            System.out.println("Neplatný index.");
            return;
        }
        Item item = player.getInventory().getItems().remove(index);
        Room room = player.getCurrentRoom();
        room.getItems().add(item);
        System.out.println("Položil jsi: " + item.getName());
    }
}