package back.SportApp.Repository;

import back.SportApp.DataBase.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
    // TOUTES LES METHODES CRUD SONT AUTOMATIQUEMENT FOURNIS DU FAIT D'ETENDRE L'INTERFACE JPAREPOSITORY pas besoin de les ecrires moi meme//
}

/*
L'interface JpaRepository hérite d'un certain nombre de méthodes utilitaires prédéfinies à partir des super-interfaces CrudRepository et PagingAndSortingRepository. Voici quelques exemples de méthodes disponibles par défaut :

        save(S entity): Sauvegarde une entité.
        saveAll(Iterable<S> entities): Sauvegarde plusieurs entités.
        findById(ID id): Trouve une entité par son ID.
        findAll(): Trouve toutes les entités.
        findAllById(Iterable<ID> ids): Trouve toutes les entités par leurs IDs.
        deleteById(ID id): Supprime une entité par son ID.
        delete(T entity): Supprime une entité.
        deleteAll(): Supprime toutes les entités.
        count(): Compte le nombre d'entités.
*/

