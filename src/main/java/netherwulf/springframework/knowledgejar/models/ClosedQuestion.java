package netherwulf.springframework.knowledgejar.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ClosedQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String codeLink;

    @OneToOne(cascade = {CascadeType.ALL})
    private Subchapter subchapter;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Quiz quiz;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "closedQuestion")
    private Set<Statement> statements = new HashSet<>();

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

    public Subchapter getSubchapter() {
        return subchapter;
    }

    public void setSubchapter(Subchapter subchapter) {
        this.subchapter = subchapter;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Set<Statement> getStatements() {
        return statements;
    }

    public void setStatements(Set<Statement> statements) {
        this.statements = statements;
    }

    public String getCodeLink() {
        return codeLink;
    }

    public void setCodeLink(String codeLink) {
        this.codeLink = codeLink;
    }
}
