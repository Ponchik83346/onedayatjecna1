package command;

import model.Player;

public class MoveRightCommand implements Command {

    private final Player player;

    public MoveRightCommand(Player player) {
        this.player = player;
    }
    /**
     * Pohne hráče na dveře zprava.
     */
    @Override
    public void execute() {
        player.moveRight();
    }
}