package fr.uga.l3miage.example.repository;

import fr.uga.l3miage.example.models.ReponseEntity;
import fr.uga.l3miage.example.models.ReponsePresentationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ReponsePresentationRepository extends JpaRepository<ReponsePresentationEntity, Integer> {

    Optional<ReponsePresentationEntity> findByReponseId(final int reponseId);


    int deleteByReponseId(final int reponseId);
}
