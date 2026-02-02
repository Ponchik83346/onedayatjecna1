package command;

import model.Player;

public class MoveLeftCommand implements Command {
    private Player player;
    public MoveLeftCommand(Player player) {
    }

    @Override
    public void execute() {
        player.moveLeft();
    }
}
