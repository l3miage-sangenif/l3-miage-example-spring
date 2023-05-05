package fr.uga.l3miage.example.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MiahootEntity {
    @Id
    @GeneratedValue
    private int miahootId;

    private String nom;

    @OneToMany(cascade = CascadeType.PERSIST)//(mappedBy = "miahoot",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<QuestionEntity> questions;

    @ManyToOne
    @JoinColumn(name = "uid")
    private UserEntity user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MiahootEntity that = (MiahootEntity) o;
        return miahootId == that.miahootId && Objects.equals(nom, that.nom) && Objects.equals(questions, that.questions) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(miahootId, nom, questions, user);
    }
}
