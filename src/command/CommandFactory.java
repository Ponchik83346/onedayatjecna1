package command;

import items.Item;
import model.Game;
import model.GameState;
import model.Player;
import ui.InputHandler;

public class CommandFactory {
    private final Player player;
    private final InputHandler inputHandler;
    private final Game game;

    public CommandFactory(Player player, InputHandler inputHandler, Game game) {
        this.player = player;
        this.inputHandler = inputHandler;
        this.game = game;
    }

    public Command create(String input) {
        input = input.toLowerCase().trim();

        switch (input) {
            case "left":
                return new MoveLeftCommand(player);

            case "right":
                return new MoveRightCommand(player);

            case "up":
                return new GoUpstairsCommand(player);

            case "down":
                return new GoDownstairsCommand(player);

            case "enter":
                return new EnterRoomCommand(player);

            case "exit room":
                return new ExitRoomCommand(player);

            case "inventory":
                return new OpenInventoryCommand(player);

            case "use":
                return new UseItemCommand(player, inputHandler);

            case "help":
                return new HelpCommand();

            case "exit game":
                return new ExitCommand(game);

            default:
                return new UnknownCommand();
        }
    }
}