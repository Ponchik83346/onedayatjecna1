package command;

import model.Player;
/**
 * Příkaz pro vstup hráče do místnosti.
 */
public class EnterRoomCommand implements Command {

    private final Player player;

    public EnterRoomCommand(Player player) {
        this.player = player;
    }
    /**
     * Provede vstup hráče do místnosti.
     */
    @Override
    public void execute() {
        player.enterRoom();
    }
}