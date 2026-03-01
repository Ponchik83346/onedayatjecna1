package command;

import model.Player;
/**
 * Příkaz pro pohyb po schodech dolů.
 */
public class GoDownstairsCommand implements Command {

    private final Player player;

    public GoDownstairsCommand(Player player) {
        this.player = player;
    }
    /**
     * Pohne s hráčem po schodech dolů.
     */
    @Override
    public void execute() {
        player.goDownstairs();
    }
}