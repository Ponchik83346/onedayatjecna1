package command;

public class HelpCommand implements Command {
    @Override
    public void execute() {
        System.out.println(
                "=== HELP ===\n" +
                        "left - jdi doleva\n" +
                        "right - jdi doprava\n" +
                        "up - jdi nahoru po schodech\n" +
                        "down - jdi dolu po schodech\n" +
                        "enter - jdi do mistnosti\n" +
                        "exit - jdi ven z mistnosti\n" +
                        "inventory - otevrit inventar\n" +
                        "use - využít předmět\n" +
                        "use elevator - využít výtah\n" +
                        "help - show help\n" +
                        "quit / exitgame - exit game\n" +
                        "pickup - vzít předmět\n"+
                        "drop - zahodit předmět"
        );
    }
}