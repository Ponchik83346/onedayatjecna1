package ui;
import model.Game;
import command.Command;
public class GameUI {

    private Game game;
    private MapRenderer renderer;
    private InputHandler input;

    public void gameLoop() {
        renderer.render(game);
    }
    public GameUI(){
        game = new Game();
        renderer = new MapRenderer(game.getMap());
        game.initialize();
    }
}