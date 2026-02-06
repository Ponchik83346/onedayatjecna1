package command;

import model.Player;

public class GoUpstairsCommand implements Command {

    private final Player player;

    public GoUpstairsCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.goUpstairs();
    }
}