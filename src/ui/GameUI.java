package ui;

import command.HelpCommand;
import map.Door;
import map.Room;
import model.Game;
import model.GameState;
import command.Command;
import command.CommandFactory;
import model.Player;
import teacher.Teacher;

/**
 * Hlavní smyčka pro zpracování vstupu hráče.
 * Kontroluje výhru, prohru, zpracovává příkazy a řeší interakce s učiteli.
 */
public class GameUI {

    private Game game;
    private MapRenderer renderer;
    private InputHandler input;
    private Thread inputThread;

    public GameUI() {
        game = new Game();
        game.initialize();
        renderer = new MapRenderer(game.getMap());
        CommandFactory factory = new CommandFactory(game.getPlayer(), game);
        input = new InputHandler(factory);
        inputThread = new Thread(this::processInputLoop);
    }
    /**
     * Zkontroluje, zda hráč stojí v místnosti s testem, a pokud ano, přidá jej hráči.
     */
    private void processInputLoop() {
        game.setState(GameState.PLAYING);
        while (game.isRunning()) {
            if (game.getPlayer().hasEnoughTests()) {
                System.out.println("Vyhrál jsi!");
                game.setState(GameState.WIN);
                break;
            }
            if (game.getPlayer().getStamina() <= 0) {
                System.out.println("Prohrál jsi! Vypršela ti stamina!");
                game.setState(GameState.LOSE);
                break;
            }
            if (game.getState() == GameState.QUIZ) {
                sleep(100);
                continue;
            }
            String text = input.processInput().toLowerCase().trim();
            if (!text.equals("wait")) {
                Command command = input.readCommand(text);
                if (command != null) {
                    command.execute();
                }
                handleTestPickup();
                if (!handleTeacherInteractions()) {
                    break;
                }
            }
            if (!game.isRunning()){
                break;
            }
            renderer.render(game);
        }
    }

    /**
     * Zkontroluje, zda je hráč ve stejné lokaci jako učitel.
     * Pokud ano, spustí kvíz.
     *
     * @return true pokud hra může pokračovat, false pokud hráč prohrál
     */
    private void handleTestPickup() {
        if (!game.getPlayer().isInsideRoom()){
            return;
        }
        Room playerRoom = game.getPlayer().getCurrentRoom();
        if (playerRoom != null && playerRoom.isHasTest()) {
            game.getPlayer().addTest();
            System.out.println("Vzali jste test!");
            playerRoom.setHasTest(false);
        }
    }

    /**
     * Spustí kvíz s daným učitelem a podle výsledku nastaví stav hry.
     * @return true při správné odpovědi, false při špatné odpovědi
     */
    private boolean handleTeacherInteractions() {
        Player player = game.getPlayer();
        Door playerDoor = player.getCurrentDoor();
        Room playerRoom = player.getCurrentRoom();
        for (Teacher t : game.getTeachers()) {
            if (t.isInsideRoom() && player.isInsideRoom() && t.getCurrentRoom() == playerRoom) {
                return startQuiz(t);
            }
            if (!t.isInsideRoom() && !player.isInsideRoom() && t.getCurrentDoor() == playerDoor) {
                return startQuiz(t);
            }
        }
        return true;
    }

    /**
     * Spustí quiz s učitelem a nastaví stav hry podle výsledku
     */
    private boolean startQuiz(Teacher t) {
        game.setState(GameState.QUIZ);
        boolean correct = t.askQuestion(game.getRandomGenerator().getRandom(), input.getScanner());
        if (!correct) {
            game.setState(GameState.LOSE);
            System.out.println("Prohrál jsi! Chytil tě učitel!");
            return false;
        } else {
            game.setState(GameState.PLAYING);
            return true;
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
    /**
     * Metoda pro přehrání intra
     */
    public void playIntro() {
        try {

            System.out.println("\n=== INTRO ===\n");

            Thread.sleep(1000);
            System.out.println("Třída je tichá...");
            Thread.sleep(1500);

            System.out.println("Slunce prosvítá oknem staré učebny.");
            Thread.sleep(1500);

            System.out.println("Hráč sedí v zadní lavici.");
            Thread.sleep(2000);

            System.out.println("\nDveře se pomalu otevřou...");
            Thread.sleep(1500);

            System.out.println("Vchází pan učitel Kuchařík.");
            Thread.sleep(1500);

            System.out.println("\"Stratil jsem všechny testy.\"");
            Thread.sleep(1500);

            System.out.println("\"Pokud je nenajdu, dostanete všichni za 5!\"");
            Thread.sleep(2000);

            System.out.println("\nMusíš najít 30 testů roztroušených po škole.");
            Thread.sleep(2000);
            Command command = new HelpCommand();
            command.execute();

            System.out.println("Začni hru...\n");
        } catch (InterruptedException ignored) {

        }
    }
}