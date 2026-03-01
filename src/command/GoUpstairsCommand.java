package command;

import model.Player;
/**
 * Příkaz pro pohyb po schodech nahorů.
 */
public class GoUpstairsCommand implements Command {

    private final Player player;

    public GoUpstairsCommand(Player player) {
        this.player = player;
    }
    /**
     * Pohne s hráčem po schodech nahorů.
     */
    @Override
    public void execute() {
        player.goUpstairs();
    }
}