package metier;

public interface IMetierBanque {
    void addCompte(Compte c);
    void verser(Long code, double montant);
    void retirer(Long code, double montant);
}
