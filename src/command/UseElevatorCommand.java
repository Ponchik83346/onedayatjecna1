package command;


import model.Player;
import ui.InputHandler;
/**
 * Příkaz pro použití výtahu.
 */
public class UseElevatorCommand implements Command {
    private InputHandler input;
    private Player player;
    /**
     * Vytvoří příkaz pro použití výtahu.
     *
     * @param player hráč používající výtah
     * @param input vstup pro výběr cílového patra
     */
    public UseElevatorCommand(Player player, InputHandler input) {
        this.player = player;
        this.input = input;
    }
    /**
     * Provede použití výtahu hráčem.
     */
    @Override
    public void execute() {
        player.useElevator(input);
    }
}