package command;
/**
 * Reprezentuje neznámý nebo neplatný příkaz.
 * Vypíše chybovou zprávu hráči.
 */
public class UnknownCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Neplatný příkaz!");
    }
}
