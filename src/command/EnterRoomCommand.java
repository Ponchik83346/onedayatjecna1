package command;

import model.Player;

public class EnterRoomCommand implements Command {

    private final Player player;

    public EnterRoomCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.enterRoom();
    }
}