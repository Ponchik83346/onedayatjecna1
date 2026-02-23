package command;

import items.Item;
import model.Game;
import model.GameState;
import model.Player;
import ui.InputHandler;

public class CommandFactory {

    private final Player player;
    private final Game game;

    public CommandFactory(Player player, Game game) {
        this.player = player;
        this.game = game;
    }

    public Command create(String input, InputHandler inputHandler) {

        input = input.toLowerCase().trim();

        return switch (input) {
            case "left" -> new MoveLeftCommand(player);
            case "right" -> new MoveRightCommand(player);
            case "up" -> new GoUpstairsCommand(player);
            case "down" -> new GoDownstairsCommand(player);
            case "enter" -> new EnterRoomCommand(player);
            case "exit" -> new ExitRoomCommand(player);
            case "inventory" -> new OpenInventoryCommand(player);
            case "use" -> new UseItemCommand(player, inputHandler);
            case "help" -> new HelpCommand();
            case "exit game" -> new ExitCommand(game);
            case "use elevator" -> new UseElevatorCommand(player, inputHandler);
            case "pickup" -> new PickupItemCommand(player, inputHandler);
            case "drop" -> new DropItemCommand(player, inputHandler);
            default -> new UnknownCommand();
        };
    }
}