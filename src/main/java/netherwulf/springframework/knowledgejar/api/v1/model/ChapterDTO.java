package netherwulf.springframework.knowledgejar.api.v1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class ChapterDTO {
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 200)
    private String description;

    private String chapterUrl;

    private Set<StudentDTO> students = new HashSet<>();
    private List<SubchapterDTO> subchapters = new ArrayList<>();

    private QuizDTO quiz;
}
