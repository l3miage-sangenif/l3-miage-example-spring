package fr.uga.l3miage.example.repository;

import fr.uga.l3miage.example.models.MiahootEntity;
import fr.uga.l3miage.example.models.MiahootPresentationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MiahootPresentationRepository extends JpaRepository<MiahootPresentationEntity, Integer> {

    //recuperation d'un miahoot particulier avec son id
    /**
     * @param miahootId de l'entité à recuper
     * @return une {@link Optional<MiahootPresentationEntity>} correspondant au miahootId donnée
     */
    Optional<MiahootPresentationEntity> findByMiahootId(final Integer miahootId);

    //suppresion d'un miahoot avec son id
    /**
     * @param miahootId de l'entité à supprimer
     */
    int deleteByMiahootId(final Integer miahootId);

    //recuperation de tous les miahoot d'un utilisateur spécifique identifié par uid
    /**
     * @param uid de l'entité à recuper
     * @return une {@link Optional<MiahootPresentationEntity>} correspondant au miahootId donnée
     */
    //List<MiahootEntity> findAllByUserUid(final String uid);
}