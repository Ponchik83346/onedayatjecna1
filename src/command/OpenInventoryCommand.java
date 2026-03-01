package command;

import model.Player;

/**
 * Příkaz pro vypsání inventáře
 */
public class OpenInventoryCommand implements Command {

    private final Player player;

    public OpenInventoryCommand(Player player) {
        this.player = player;
    }

    /**
     * Vypíše inventář do konzole.
     */
    @Override
    public void execute() {
        player.openInventory();
    }
}