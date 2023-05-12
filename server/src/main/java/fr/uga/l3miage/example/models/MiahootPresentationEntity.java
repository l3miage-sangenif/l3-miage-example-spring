package fr.uga.l3miage.example.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MiahootPresentationEntity {
    @Id
    @GeneratedValue
    private int miahootId;

    private String nom;

    private boolean enCours;

    @OneToMany(cascade = CascadeType.PERSIST)//(mappedBy = "miahoot",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<QuestionPresentationEntity> questions;

    @ManyToOne
    private UserEntity user;

    @ManyToMany(mappedBy = "miahootParticipes",cascade = CascadeType.PERSIST)//(mappedBy = "question",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<UserEntity> participants;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MiahootPresentationEntity that = (MiahootPresentationEntity) o;
        return miahootId == that.miahootId && Objects.equals(nom, that.nom) && Objects.equals(questions, that.questions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(miahootId, nom, questions);
    }
}
