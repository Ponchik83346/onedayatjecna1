package ui;
import model.Game;
import command.Command;
import command.CommandFactory;
import model.GameState;

public class GameUI {

    private Game game;
    private MapRenderer renderer;
    private InputHandler input;

    public void gameLoop() {
        while (game.isRunning()) {
            renderer.render(game);
            String inputStr = input.processInput();
            Command command = input.readCommand(inputStr);
            if (command != null) {
                command.execute();
            }
            game.update(input);
        }
        System.out.println("Hra ukončena: " + game.getState());
    }

    public GameUI() {
        game = new Game();
        game.initialize();
        renderer = new MapRenderer(game.getMap());
        CommandFactory factory = new CommandFactory(game.getPlayer(), input, game);
        input = new InputHandler(factory);
    }
}