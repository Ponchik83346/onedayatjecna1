package command;

import model.Player;
/**
 * Příkaz pro opuštění aktuální místnosti.
 */
public class ExitRoomCommand implements Command {

    private final Player player;

    public ExitRoomCommand(Player player) {
        this.player = player;
    }
    /**
     * Provede odchod hráče z místnosti.
     */
    @Override
    public void execute() {
        player.exitRoom();
    }
}