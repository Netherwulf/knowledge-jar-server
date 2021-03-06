package netherwulf.springframework.knowledgejar.models;

import javax.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String isCorrect;
    private String replyDate;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Student student;

    @ManyToOne(cascade = {CascadeType.ALL})
    private OpenQuestion openQuestion;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Statement statement;

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

    public String getCorrect() {
        return isCorrect;
    }

    public void setCorrect(String correct) {
        isCorrect = correct;
    }

    public String getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public OpenQuestion getOpenQuestion() {
        return openQuestion;
    }

    public void setOpenQuestion(OpenQuestion openQuestion) {
        this.openQuestion = openQuestion;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }
}
