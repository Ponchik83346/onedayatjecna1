package command;


import model.Player;

public class UseElevatorCommand implements Command {
    private Player player;

    public UseElevatorCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.useElevator();
    }
}