package command;
import items.Item;
import map.Room;
import model.Game;
import model.Player;
import ui.InputHandler;
/**
 * Příkaz pro sebrání předmětu z aktuální místnosti.
 */
public class PickupItemCommand implements Command {

    private Player player;
    private InputHandler inputHandler;
    /**
     * Vytvoří příkaz pro sbírání předmětů.
     * @param player hráč, který předmět sbírá
     * @param inputHandler vstup pro načtení indexu
     */
    public PickupItemCommand(Player player, InputHandler inputHandler) {
        this.player = player;
        this.inputHandler = inputHandler;
    }
    /**
     * Provede sebrání předmětu.
     * Hráč musí být v místnosti a vybrat platný index předmětu.
     */
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
        inputHandler.getScanner().nextLine();
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
