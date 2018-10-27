package netherwulf.springframework.knowledgejar.api.v1.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import netherwulf.springframework.knowledgejar.domain.Subchapter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;

@Data
@NoArgsConstructor
public class ChapterDTO {
    private Long id;

    @NotBlank
    @Size(max = 30)
    private String name;

    @NotBlank
    @Size(max = 100)
    private String description;

    private Set<StudentDTO> students = new HashSet<>();
    private Set<SubchapterDTO> subchapters = new HashSet<>();

    private QuizDTO quiz;
}
