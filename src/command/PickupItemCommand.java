package command;
import items.Item;
import map.Room;
import model.Game;
import model.Player;
import ui.InputHandler;
public class PickupItemCommand implements Command {

    private Player player;
    private InputHandler inputHandler;

    public PickupItemCommand(Player player, InputHandler inputHandler) {
        this.player = player;
        this.inputHandler = inputHandler;
    }

    @Override
    public void execute() {
        if (!player.isInsideRoom()) {
            System.out.println("Nejsi v místnosti.");
            return;
        }

        Room room = player.getCurrentRoom();

        if (room.getItems().isEmpty()) {
            System.out.println("Místnost je prázdná.");
            return;
        }

        System.out.print("Vyber index itemu: ");

        int index = inputHandler.getScanner().nextInt();
        inputHandler.getScanner().nextLine(); // FIX

        if (index < 0 || index >= room.getItems().size()) {
            System.out.println("Neplatný index.");
            return;
        }

        Item item = room.getItems().get(index);

        player.getInventory().addItem(item);
        room.getItems().remove(index);

        System.out.println("Sebral jsi: " + item.getName());
    }
}
