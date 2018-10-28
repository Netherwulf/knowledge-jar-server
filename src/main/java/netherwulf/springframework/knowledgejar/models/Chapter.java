package netherwulf.springframework.knowledgejar.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToMany(mappedBy = "chapters")
    private Set<Student> students;

    @OneToOne(fetch = FetchType.EAGER)
    private Quiz quiz;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chapter")
    private Set<Subchapter> subchapters = new HashSet<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Set<Subchapter> getSubchapters() {
        return subchapters;
    }

    public void setSubchapters(Set<Subchapter> subchapters) {
        this.subchapters = subchapters;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
