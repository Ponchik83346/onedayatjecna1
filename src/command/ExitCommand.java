package command;

import model.Game;
import model.GameState;

public class ExitCommand implements Command {
    @Override
    public void execute() {

    }
    public ExitCommand(Game game) {
        game.setState(GameState.EXIT);
    }
}
