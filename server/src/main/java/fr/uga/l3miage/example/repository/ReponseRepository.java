package fr.uga.l3miage.example.repository;

import fr.uga.l3miage.example.models.ReponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



@Repository
public interface ReponseRepository extends JpaRepository<ReponseEntity, Integer> {

    Optional<ReponseEntity> findByReponseId(final int reponseId);


    int deleteByReponseId(final int reponseId);
}
