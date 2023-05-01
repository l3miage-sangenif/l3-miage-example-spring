
package fr.uga.l3miage.example.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ReponseEntity {
    @Id
    @GeneratedValue
    private int reponseId;

    private String label;

    private Boolean estValide;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity question;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReponseEntity that = (ReponseEntity) o;
        return reponseId == that.reponseId && Objects.equals(label, that.label) && Objects.equals(estValide, that.estValide) && Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reponseId, label, estValide, question);
    }
}