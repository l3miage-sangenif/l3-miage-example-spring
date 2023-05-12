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
public class QuestionPresentationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;

    private String label;

    private boolean questionEnCours;

    @OneToMany(cascade = CascadeType.PERSIST)//(mappedBy = "question",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<ReponsePresentationEntity> reponses;
/*
    @ManyToOne
    @JoinColumn(name="miahootId")
    private MiahootEntity miahoot;
*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionPresentationEntity that = (QuestionPresentationEntity) o;
        return questionId == that.questionId && Objects.equals(label, that.label) && Objects.equals(reponses, that.reponses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, label, reponses);
    }
}
