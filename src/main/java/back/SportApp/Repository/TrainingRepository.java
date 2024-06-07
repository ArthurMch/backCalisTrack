package back.SportApp.Repository;

import back.SportApp.DataBase.Account;
import back.SportApp.DataBase.Training;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingRepository extends JpaRepository<Training, Integer> {
    // TOUTES LES METHODES CRUD SONT AUTOMATIQUEMENT FOURNIS DU FAIT D'ETENDRE L'INTERFACE JPAREPOSITORY pas besoin de les ecrires moi meme//
}
