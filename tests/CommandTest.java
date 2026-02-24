import command.Command;
import command.CommandFactory;
import command.MoveLeftCommand;
import command.MoveRightCommand;
import command.UnknownCommand;
import model.Game;
import model.Player;
import org.junit.jupiter.api.Test;
import ui.InputHandler;

import static org.junit.Assert.assertTrue;

public class CommandTest {
    @Test
    void leftCommand() {
        Player player = new Player();
        Game game = new Game();
        CommandFactory factory = new CommandFactory(player, game);
        Command command = factory.create("left", new InputHandler(null));
        assertTrue(command instanceof MoveLeftCommand);
    }
    @Test
    void rightCommand() {
        CommandFactory factory = new CommandFactory(new Player(), new Game());
        Command command = factory.create("right", new InputHandler(null));
        assertTrue(command instanceof MoveRightCommand);
    }
    @Test
    void casse() {
        CommandFactory factory = new CommandFactory(new Player(), new Game());
        Command command = factory.create("LeFt", new InputHandler(null));
        assertTrue(command instanceof MoveLeftCommand);
    }
    @Test
    void trim() {
        CommandFactory factory = new CommandFactory(new Player(), new Game());
        Command command = factory.create("  right                    ", new InputHandler(null));
        assertTrue(command instanceof MoveRightCommand);
    }
    @Test
    void unknownCommand() {
        CommandFactory factory = new CommandFactory(new Player(), new Game());
        Command command = factory.create("hdrahsjhdsbdfgraegbzdfb", new InputHandler(null));
        assertTrue(command instanceof UnknownCommand);
    }
}
