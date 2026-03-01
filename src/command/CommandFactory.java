package command;

import items.Item;
import model.Game;
import model.GameState;
import model.Player;
import ui.InputHandler;
/**
 * Vytváří příkazy podle textového vstupu hráče.
 * Převádí zadaný text na konkrétní Command objekt.
 */
public class CommandFactory {

    private final Player player;
    private final Game game;
    /**
     * Vytvoří CommandFactory.
     * @param player hráč, nad kterým se budou příkazy vykonávat
     * @param game instance hry
     */
    public CommandFactory(Player player, Game game) {
        this.player = player;
        this.game = game;
    }
    /**
     * Vytvoří konkrétní příkaz podle vstupu hráče.
     * Pokud příkaz neexistuje, vrátí UnknownCommand.
     * @param input input hráče
     * @param inputHandler pomocná třída pro další vstupy
     * @return vytvořený Command
     */
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