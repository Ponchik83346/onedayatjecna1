package command;

import model.Player;
/**
 * Příkaz pro pohyb doleva.
 */
public class MoveLeftCommand implements Command {

    private final Player player;

    public MoveLeftCommand(Player player) {
        this.player = player;
    }
    /**
     * Pohne hráče na dveře zleva.
     */
    @Override
    public void execute() {
        player.moveLeft();
    }
}