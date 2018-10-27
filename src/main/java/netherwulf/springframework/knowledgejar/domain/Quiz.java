package netherwulf.springframework.knowledgejar.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    private Chapter chapter;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quiz")
    private Set<OpenQuestion> openQuestions = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quiz")
    private Set<ClosedQuestion> closedQuestions = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Set<OpenQuestion> getOpenQuestions() {
        return openQuestions;
    }

    public void setOpenQuestions(Set<OpenQuestion> openQuestions) {
        this.openQuestions = openQuestions;
    }

    public Set<ClosedQuestion> getClosedQuestions() {
        return closedQuestions;
    }

    public void setClosedQuestions(Set<ClosedQuestion> closedQuestions) {
        this.closedQuestions = closedQuestions;
    }
}
