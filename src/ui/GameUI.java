package ui;
import map.Door;
import map.Room;
import model.Game;
import command.Command;
import command.CommandFactory;
import model.GameState;
import teacher.Teacher;

public class GameUI {

    private Game game;
    private MapRenderer renderer;
    private InputHandler input;
    private Thread thread = new Thread(() -> {
        game.setState(GameState.PLAYING);
        while (game.isRunning()) {
            if (game.getState() == GameState.QUIZ) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            String text = input.processInput();
            text = text.trim();
            text = text.toLowerCase();
            if (!text.equals("wait")) {


            Command command = input.readCommand(text);
            if (command != null) command.execute();
            Door playerDoor = game.getPlayer().getCurrentDoor();
            Room playerRoom = game.getPlayer().getCurrentRoom();
            for (Teacher t : game.getTeachers()) {
                boolean sameDoor = t.getCurrentDoor() != null && t.getCurrentDoor() == playerDoor;
                boolean sameRoom = t.getCurrentRoom() != null && t.getCurrentRoom() == playerRoom;
                if (sameDoor || sameRoom) {
                    game.setState(GameState.QUIZ);
                    boolean correct = t.askQuestion(game.getRandomGenerator().getRandom(), input.getScanner());
                    if (!correct) {
                        game.setState(GameState.LOSE);
                        System.out.println("GAME OVER");
                    }
                    game.setState(GameState.PLAYING);
                }
            }
            }
            renderer.render(game);
        }
    });
    /**
     * 2 thread method
     * Jeden thread checkuje input hrace, druhy updatuje ucitele.
     */
    public void gameLoop() {
        //prvni render hry
        renderer.render(game);
        //thread pro input
        thread.start();
        //update ucitelu
        final int UPDATE_FPS = 20;
        final long frameTime = 1000 / UPDATE_FPS;
        while (game.isRunning()) {
            if (game.getState() == GameState.QUIZ) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {}
                continue;
            }
            long start = System.nanoTime();
            game.updateTeachers();
            long elapsed = System.nanoTime() - start;
            long sleep = frameTime - elapsed / 1_000_000;
            if (sleep > 0) {
                try { Thread.sleep(sleep); }
                catch (InterruptedException e) {}
            }
        }
    }

    public GameUI() {
        game = new Game();
        game.initialize();
        renderer = new MapRenderer(game.getMap());
        CommandFactory factory = new CommandFactory(game.getPlayer(), input, game);
        input = new InputHandler(factory);
    }
}