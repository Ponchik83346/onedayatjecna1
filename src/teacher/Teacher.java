package teacher;
import items.Material;
import map.Door;
import map.Room;
import model.GameCharacter;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import ui.InputHandler;

/**
 * Reprezentuje učitele ve hře.
 * Učitel se může pohybovat po mapě pomocí jednoduché AI, zkouší hráče otázkami a při špatné odpovědi hráč prohrává.
 * Teacher sdílí pohybovou logiku s Player
 */
public class Teacher extends GameCharacter {

    private String name;
    private int aiLevel;
    private QuestionSet questions;
    private int timeLimit;
    private Door startDoor;

    public Teacher(String name, int aiLevel, QuestionSet questions, Door startDoor, int timeLimit) {
        this.name = name;
        this.aiLevel = aiLevel;
        this.questions = questions;
        this.timeLimit = timeLimit;
        this.currentDoor = startDoor;
        this.startDoor = startDoor;
        this.currentRoom = null;
    }

    public String getName() {
        return name;
    }

    /**
     * Pokusí se vstoupit do místnosti, pokud učitel není již uvnitř.
     * Učitel se přidá do seznamu učitelů v aktuální místnosti.
     * Pokud je místnost plná, vstup se nezdaří.
     */
    @Override
    public void enterRoom() {
        if (!insideRoom) {
            if(currentDoor.getConnectedRoom().addTeacher(this)){
                insideRoom = true;
                currentRoom = currentDoor.getConnectedRoom();
                setCurrentDoor(null);
            }
        }
    }
    /**
     * Opustí místnost, pokud se učitel nachází uvnitř.
     * Učitel se přesune zpět na dveře místnosti a resetuje
     * referenci na aktuální místnost.
     */
    @Override
    public void exitRoom() {
        if (insideRoom) {
            insideRoom = false;
            currentRoom.removeTeacher(this);
            setCurrentDoor(currentRoom.getDoor());
            currentRoom = null;
        }
    }
    /**
     * Simuluje jednoduchou AI pohyb učitele.
     * Pokud je učitel v místnosti, existuje malá pravděpodobnost, že ji opustí. Jinak se pohybuje doleva, doprava nebo vstoupí do místnosti.
     * Pokud je dveře zamčené, učitel postupně snižuje životnost materiálu který je na dveřech.
     * @param rand generátor náhodných čísel pro AI rozhodování
     */
    public void moveAI(Random rand) {
        if (isInsideRoom()) {
            if (rand.nextDouble() < 0.15) exitRoom();
            return;
        }
        if (currentDoor == null) return;
        Room room = getCurrentRoom();
        if (room != null && room.getDoor().isLocked()) {
            Material mat = room.getDoor().getMaterial();
            if (mat != null) {
                mat.setHp(mat.getHp() - 1);
                if (mat.getHp() <= 0) room.getDoor().setLocked(false);
            }
        }
        double r = Math.random();
        if (r < 0.4) moveLeft();
        else if (r < 0.8) moveRight();
        else enterRoom();
    }

    /**
     * Spustí kvízovou interakci mezi učitelem a hráčem.
     * Učitel náhodně vybere otázku ze své sady otázek a čeká na odpověď hráče.
     * Podmínky úspěchu:
     * - hráč zadá správnou odpověď
     * - odpověď je zadána v časovém limitu
     * Při úspěchu hráče se učitel vrátí k jeho kabinetu.
     * @param rand generátor náhodných čísel
     * @param input scanner pro načtení odpovědi hráče
     * @return true pokud hráč odpověděl správně a včas, jinak false
     */
    public boolean askQuestion(Random rand, Scanner input) {
        Question q = questions.getQuestions().get(rand.nextInt(questions.getQuestions().size()));
        System.out.println(q);
        long start = System.currentTimeMillis();
        String answer = input.nextLine();
        long end = System.currentTimeMillis();
        long seconds = (end - start) / 1000;
        if (answer == null || answer.isBlank()) {
            System.out.println("Nezadali jste odpověď!");
            return false;
        }
        if (seconds > timeLimit) {
            System.out.println("Čas vypršel! (limit: " + timeLimit + " s)");
            return false;
        }
        answer = answer.toUpperCase();
        if (q.isCorrect(answer)) {
            System.out.println("Správně!");
            this.insideRoom = false;
            this.currentRoom = null;
            this.currentDoor = startDoor;
            return true;
        } else {
            System.out.println("Špatně!");
            return false;
        }
    }
}