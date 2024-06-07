package back.SportApp.Repository;

import back.SportApp.DataBase.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    // TOUTES LES METHODES CRUD SONT AUTOMATIQUEMENT FOURNIS DU FAIT D'ETENDRE L'INTERFACE JPAREPOSITORY pas besoin de les ecrires moi meme //
}
