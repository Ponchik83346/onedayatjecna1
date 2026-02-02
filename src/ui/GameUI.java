package ui;
import model.Game;
import command.Command;
import model.GameState;

public class GameUI {

    private Game game;
    private MapRenderer renderer;
    private InputHandler input;

    public void gameLoop() {
        while (game.getState() == GameState.PLAYING) {
            renderer.render(game);
            String inputStr = input.processInput();
            Command command = input.readCommand(inputStr);
            if (command != null) {
                command.execute();
            }
            game.update();
        }
    }
    public GameUI(){
        game = new Game();
        renderer = new MapRenderer(game.getMap());
        game.initialize();
    }
}