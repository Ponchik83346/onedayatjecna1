package ui;

import map.Door;
import map.Room;
import model.Game;
import model.GameState;
import command.Command;
import command.CommandFactory;
import teacher.Teacher;

public class GameUI {

    private Game game;
    private MapRenderer renderer;
    private InputHandler input;
    private Thread inputThread;

    public GameUI() {
        game = new Game();
        game.initialize();
        renderer = new MapRenderer(game.getMap());
        input = new InputHandler(null);
        CommandFactory factory = new CommandFactory(game.getPlayer(), input, game);
        input.setCommandFactory(factory);
        inputThread = new Thread(this::processInputLoop);
    }
    /**
     * Hlavní loop pro zpracování inputu hráče a interakce s učiteli.
     */
    private void processInputLoop() {
        game.setState(GameState.PLAYING);
        while (game.isRunning()) {
            // Výhra
            if (game.getPlayer().hasEnoughTests()) {
                System.out.println("Vyhrál jsi!");
                break;
            }
            if (game.getState() == GameState.QUIZ) {
                sleep(100);
                continue;
            }
            if(game.getPlayer().getStamina()==0){
                System.out.println("Prohrál jsi! Vypršela ti stamina!");
            }
            String text = input.processInput().trim().toLowerCase();
            if (!text.equals("wait")) {
                Command command = input.readCommand(text);
                if (command != null) command.execute();

                handleTestPickup();
                handleTeacherInteractions();
            }
            renderer.render(game);
        }
    }

    /**
     * Kontrola a sběr testů, pokud jsou ve stejné místnosti
     */
    private void handleTestPickup() {
        Room playerRoom = game.getPlayer().getCurrentRoom();
        if (playerRoom != null && playerRoom.isHasTest()) {
            game.getPlayer().addTest();
            System.out.println("Vzali jste test!");
            playerRoom.setHasTest(false);
        }
    }

    /**
     * Kontrola interakce s učiteli (místnost/chodba)
     */
    private void handleTeacherInteractions() {
        Door playerDoor = game.getPlayer().getCurrentDoor();
        Room playerRoom = game.getPlayer().getCurrentRoom();

        for (Teacher t : game.getTeachers()) {
            if (t.isInsideRoom() && playerRoom != null && t.getCurrentRoom() == playerRoom) {
                startQuiz(t);
                continue;
            }
            if (!t.isInsideRoom() && playerRoom == null && t.getCurrentDoor() != null && t.getCurrentDoor() == playerDoor) {
                startQuiz(t);
            }
        }
    }

    /**
     * Spustí quiz s učitelem a nastaví stav hry podle výsledku
     */
    private void startQuiz(Teacher t) {
        game.setState(GameState.QUIZ);
        boolean correct = t.askQuestion(game.getRandomGenerator().getRandom(), input.getScanner());
        if (!correct) {
            game.setState(GameState.LOSE);
            System.out.println("Prohrál jsi! Chytil tě učitel!");
        } else {
            game.setState(GameState.PLAYING);
        }
    }

    /**
     * Hlavní game loop - spouští input thread a update učitelů
     */
    public void gameLoop() {
        renderer.render(game);
        inputThread.start();

        final int UPDATE_FPS = 20;
        final long frameTime = 1000 / UPDATE_FPS;

        while (game.isRunning()) {
            if (game.getState() == GameState.QUIZ) {
                sleep(100);
                continue;
            }

            long start = System.nanoTime();
            game.updateTeachers();
            long elapsed = System.nanoTime() - start;
            long sleepTime = frameTime - elapsed / 1_000_000;
            if (sleepTime > 0) sleep(sleepTime);
        }
    }

    /**
     * Utility metoda pro sleep
     */
    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }
}