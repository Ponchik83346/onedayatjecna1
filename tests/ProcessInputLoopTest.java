import model.Game;
import model.GameState;
import model.Player;
import org.junit.jupiter.api.Test;
import ui.GameUI;
import static org.junit.Assert.assertTrue;
public class ProcessInputLoopTest {
    @Test
    void processInputLoop() {
        Game game = new Game();
        Player player = game.getPlayer();
        player.setTestsCollected(30);
        GameUI ui = new GameUI();
        game.setState(GameState.PLAYING);
        assertTrue(player.hasEnoughTests());
    }
}