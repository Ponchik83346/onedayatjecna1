import command.CommandFactory;
import items.Food;
import items.Hammer;
import items.Key;
import items.Material;
import model.Game;
import model.Player;
import org.junit.jupiter.api.Test;
import ui.InputHandler;

import java.util.Scanner;

import static org.junit.Assert.*;

public class UseItemTest {
    @Test
    void itemNotInInventory() {
        Game game = new Game();
        Player player = game.getPlayer();
        InputHandler input = new InputHandler(new CommandFactory(player, game));
        Food food = new Food(10, "Bageta", 1, 1, 1);
        int staminaBefore = player.getStamina();
        player.useItem(food, input);
        assertEquals(staminaBefore, player.getStamina());
    }
    @Test
    void useFood() {
        Game game = new Game();
        Player player = game.getPlayer();
        Food food = new Food(10, "Bageta", 1, 1, 1);
        player.getInventory().addItem(food);
        InputHandler input = new InputHandler(new CommandFactory(player, game));
        int staminaBefore = player.getStamina();
        player.useItem(food, input);
        assertEquals(staminaBefore + 10, player.getStamina());
        assertFalse(player.getInventory().contains(food));
    }
    @Test
    void useKey() {
        Game game = new Game();
        Player player = game.getPlayer();
        Key key = new Key("Key", 100);
        player.getInventory().addItem(key);
        InputHandler input = new InputHandler(new CommandFactory(player, game));
        player.useItem(key, input);
        assertFalse(player.getInventory().contains(key));
    }
    @Test
    void useHammer() {
        Game game = new Game();
        Player player = game.getPlayer();
        Hammer hammer = new Hammer("Hammer", 2);
        player.getInventory().addItem(hammer);
        InputHandler input = new InputHandler(new CommandFactory(player, game));
        int staminaBefore = player.getStamina();
        player.useItem(hammer, input);
        assertEquals(staminaBefore, player.getStamina());
    }
    @Test
    void useMaterial() {
        Game game = new Game();
        Player player = game.getPlayer();
        Material material = new Material(5, "Wood", 50);
        player.getInventory().addItem(material);
        InputHandler input = new InputHandler(new CommandFactory(player, game));
        player.useItem(material, input);
        assertTrue(player.getInventory().contains(material));
    }
}
