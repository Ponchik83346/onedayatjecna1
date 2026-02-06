package command;

public class HelpCommand implements Command {
    @Override
    public void execute() {
            System.out.println("=== HELP ===");
            System.out.println("left - jdi doleva");
            System.out.println("right - jdi doprava");
            System.out.println("up - jdi nahoru po schodech");
            System.out.println("down - jdi dolu po schodech");
            System.out.println("enter - jdi do mistnosti");
            System.out.println("exit - jdi ven z mistnosti");
            System.out.println("inventory - otevrit inventar");
            System.out.println("use - využít předmět");
            System.out.println("use elevator - využít výtah");
            System.out.println("help - show help");
            System.out.println("quit / exitgame - exit game");
    }
}