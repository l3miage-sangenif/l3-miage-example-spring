
package fr.uga.l3miage.example.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    private Long uid;

    private String nom;

    private boolean estEnseignant;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<MiahootEntity> miahoots;

    @OneToMany
    private List<ReponseEntity> reponses;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserEntity that = (UserEntity) o;
        return getUid() != null && Objects.equals(getUid(), that.getUid());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}

