package command;

import model.Player;

public class OpenInventoryCommand implements Command {
    private Player player;

    public OpenInventoryCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.getInventory().printContents();
    }
}