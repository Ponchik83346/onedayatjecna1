package command;

import model.Game;
import model.Player;

public class CommandFactory {

    private Player player;
    private Game game;

    public CommandFactory(Player player, Game game) {
        this.player = player;
        this.game = game;
    }

    public Command create(String input) {

        switch(input.toLowerCase()) {

            case "left":
                return new MoveLeftCommand(player);

            case "right":
                return new MoveRightCommand(player);

            case "enter":
                return new GoInsideRoomCommand(player);

            case "inventory":
                return new OpenInventoryCommand(player);

            case "use":
                return new UseItemCommand(player);

            case "help":
                return new HelpCommand();

            case "exit":
                return new ExitCommand(game);

            default:
                return new UnknownCommand();
        }
    }
}