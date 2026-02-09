package command;

import items.Material;
import model.Player;
import map.Door;
import ui.InputHandler;

import java.util.List;
import java.util.Scanner;

public class BarricadeCommand implements Command {
    private Player player;
    private InputHandler input;

    public BarricadeCommand(Player player, InputHandler input) {
        this.player = player;
        this.input = input;
    }

    @Override
    public void execute() {
        Door door = player.getCurrentDoor();
        if (door == null) {
            System.out.println("Jsi v místnosti!");
            return;
        }

        List<Material> materials = player.getInventory().getMaterials();
        if (materials.isEmpty()) {
            System.out.println("Nemáte žádné materiály k barikádování!");
            return;
        }

        System.out.println("Vyberte materiál pro zabarikádování dveří:");
        for (int i = 0; i < materials.size(); i++) {
            System.out.println(i + ": " + materials.get(i).getName());
        }

        Scanner sc = input.getScanner();
        int choice = sc.nextInt();

        if (choice < 0 || choice >= materials.size()) {
            System.out.println("Neplatná volba materiálu.");
            return;
        }

        Material chosenMat = materials.get(choice);
        door.setLocked(true);
        door.setMaterial(chosenMat);
        player.getInventory().removeItem(chosenMat);

        System.out.println("Zabarikádovali jste dveře pomocí: " + chosenMat.getName());
    }
}