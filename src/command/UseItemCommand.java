package command;

import items.Item;
import model.Player;

import java.util.Scanner;

public class UseItemCommand implements Command {
    private final Player player;
    private final Item item;
    private final Scanner scanner;

    public UseItemCommand(Player player, Item item, Scanner scanner) {
        this.player = player;
        this.item = item;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        player.useItem(item, scanner);
    }
}