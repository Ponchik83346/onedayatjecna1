package command;


import model.Player;
import ui.InputHandler;

public class UseElevatorCommand implements Command {
    private InputHandler input;
    private Player player;

    public UseElevatorCommand(Player player, InputHandler input) {
        this.player = player;
        this.input = input;
    }

    @Override
    public void execute() {
        player.useElevator(input);
    }
}