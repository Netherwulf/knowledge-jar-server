package netherwulf.springframework.knowledgejar.api.v1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class QuizDTO {
    private Long id;

    @NotNull
    @Size(max = 30)
    private String name;

    private String quizUrl;

    private Long chapterId;

    private Set<OpenQuestionDTO> openQuestions = new HashSet<>();
    private Set<ClosedQuestionDTO> closedQuestions = new HashSet<>();
}
