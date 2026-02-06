package command;

public class UnknownCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Neplatný příkaz!");
    }
}
