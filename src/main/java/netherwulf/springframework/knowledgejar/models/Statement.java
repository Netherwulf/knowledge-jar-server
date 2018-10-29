package netherwulf.springframework.knowledgejar.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Statement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String isCorrect;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statement")
    private Set<Answer> answers = new HashSet<>();

    @ManyToOne(cascade = {CascadeType.ALL})
    private ClosedQuestion closedQuestion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(String isCorrect) {
        this.isCorrect = isCorrect;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public ClosedQuestion getClosedQuestion() {
        return closedQuestion;
    }

    public void setClosedQuestion(ClosedQuestion closedQuestion) {
        this.closedQuestion = closedQuestion;
    }
}
