package back.SportApp.Service;

import back.SportApp.DataBase.Account;

import java.util.List;

public interface tableService <Objet> {
    Object creer (Objet objet);

    List<Objet> lire ();

    Objet lireById(Integer id);

    Objet modifier(Integer id, Objet objet);

    String supprimer(Integer id);
}
