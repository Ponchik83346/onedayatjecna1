package command;

import model.Player;

public class ExitRoomCommand implements Command {

    private final Player player;

    public ExitRoomCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.exitRoom();
    }
}