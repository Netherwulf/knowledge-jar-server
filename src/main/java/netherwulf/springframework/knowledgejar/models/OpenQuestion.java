package netherwulf.springframework.knowledgejar.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class OpenQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String correctAnswer;
    private String codeLink;

    @OneToOne(cascade = {CascadeType.ALL})
    private Subchapter subchapter;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "openQuestion")
    private Set<Answer> answers = new HashSet<>();

    @ManyToOne(cascade = {CascadeType.ALL})
    private Quiz quiz;

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

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Subchapter getSubchapter() {
        return subchapter;
    }

    public void setSubchapter(Subchapter subchapter) {
        this.subchapter = subchapter;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getCodeLink() {
        return codeLink;
    }

    public void setCodeLink(String codeLink) {
        this.codeLink = codeLink;
    }
}
