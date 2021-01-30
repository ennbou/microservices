package metier;

import java.util.HashMap;
import java.util.Map;

public class MetierBanqueImplementation implements IMetierBanque {

    private Map<Long, Compte> comptes = new HashMap<>();

    @Override
    public void addCompte(Compte c) {
        comptes.put(c.getCode(), c);
    }

    @Override
    public void verser(Long code, double montant) {
        Compte c = comptes.get(code);
        c.setSolde(c.getSolde() + montant);
    }

    @Override
    public void retirer(Long code, double montant) {
        Compte c = comptes.get(code);
        c.setSolde(c.getSolde() - montant);
    }
}
