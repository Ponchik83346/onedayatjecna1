package command;

import model.Game;
import model.GameState;
/**
 * Příkaz pro ukončení hry.
 * Nastaví stav hry na EXIT.
 */
public class ExitCommand implements Command {
    private final Game game;
    public ExitCommand(Game game){
        this.game = game;
    }
    /**
     * Ukončí hru změnou jejího stavu na EXIT.
     */
    @Override
    public void execute(){
        game.setState(GameState.EXIT);
    }
}