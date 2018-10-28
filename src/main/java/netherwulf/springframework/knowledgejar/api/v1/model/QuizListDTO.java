package netherwulf.springframework.knowledgejar.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizListDTO {

    List<QuizDTO> quizzes;
}
