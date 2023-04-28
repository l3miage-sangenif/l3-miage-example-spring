package fr.uga.l3miage.example.repository;

import fr.uga.l3miage.example.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUid(final Long uid);

    int deleteByUid(final Long uid);
}