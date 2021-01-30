import metier.Compte;
import metier.IMetierBanque;
import metier.MetierBanqueImplementation;

public class App {
    public static void main(String[] args) {
        IMetierBanque m = new MetierBanqueImplementation();
        Compte c = new Compte(1l, 190);
        m.addCompte(c);
    }
}
