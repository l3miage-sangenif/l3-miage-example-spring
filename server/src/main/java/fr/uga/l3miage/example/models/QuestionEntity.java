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
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;

    private String label;

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<ReponseEntity> reponses;

    @ManyToOne
    @JoinColumn(name="miahoot_id")
    private MiahootEntity miahoot;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionEntity that = (QuestionEntity) o;
        return questionId == that.questionId && Objects.equals(label, that.label) && Objects.equals(reponses, that.reponses) && Objects.equals(miahoot, that.miahoot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, label, reponses, miahoot);
    }
}
