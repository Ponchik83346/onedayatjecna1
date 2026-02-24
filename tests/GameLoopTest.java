import model.Game;
import model.GameState;
import org.junit.jupiter.api.Test;
import ui.GameUI;

import static org.junit.Assert.assertTrue;

public class GameLoopTest {
    @Test
    void gameLoop() {
        Game game = new Game();
        game.setState(GameState.QUIZ);
        GameUI ui = new GameUI();
        long start = System.currentTimeMillis();
        ui.gameLoop();
        long elapsed = System.currentTimeMillis() - start;
        assertTrue(elapsed >= 100);
    }
}
