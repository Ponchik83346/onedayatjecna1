package command;

import model.Game;
import model.GameState;

public class ExitCommand implements Command {
    private final Game game;
    public ExitCommand(Game game){
        this.game = game;
    }
    @Override
    public void execute(){
        game.setState(GameState.EXIT);
    }
}