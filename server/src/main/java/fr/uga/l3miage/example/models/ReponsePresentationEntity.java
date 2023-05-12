
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
public class ReponsePresentationEntity {
    @Id
    @GeneratedValue
    private int reponseId;

    private String label;

    private Boolean estValide;

    @ManyToMany(mappedBy = "reponseDonnes",cascade = CascadeType.PERSIST)//(mappedBy = "question",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<UserEntity> users;
/*
    @ManyToOne
    @JoinColumn(name = "questionId")
    private QuestionEntity question;
*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReponsePresentationEntity that = (ReponsePresentationEntity) o;
        return reponseId == that.reponseId && Objects.equals(label, that.label) && Objects.equals(estValide, that.estValide);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reponseId, label, estValide);
    }
}