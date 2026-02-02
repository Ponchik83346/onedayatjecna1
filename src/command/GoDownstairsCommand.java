package command;

import model.Player;

public class GoDownstairsCommand implements Command {
    private Player player;

    public GoDownstairsCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.goDownstairs();
    }
}