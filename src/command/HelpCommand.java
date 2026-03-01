package command;
/**
 * Příkaz pro vypsání nápovědy.
 * Zobrazí seznam dostupných příkazů a legendu mapy.
 */
public class HelpCommand implements Command {
    /**
     * Vypíše nápovědu do konzole.
     */
    @Override
    public void execute() {
        System.out.println(
                "=== HELP ===\n\n" + "== PŘÍKAZY ==\n"+
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
                        "drop - zahodit předmět\n\n"+
                        "wait - počkej jednu minutu na místě (vyrenderuje se mapa)"+
                        "== LEGENDA ==\n"+
                        "X - zamčené dveře\n"+
                        ". - učebna\n"+
                        "S - schody\n"+
                        "V - výtah\n"+
                        "C - kabinet učitele\n"+
                        "D - dílna\n"+
                        "B - bufet\n"+
                        "R - ředitelna\n"
        );
    }
}