package netherwulf.springframework.knowledgejar.api.v1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class StatementDTO {
    private Long id;

    @NotBlank
    @Size(max = 30)
    private String content;

    @NotBlank
    private Boolean isCorrect;

    private String statementUrl;

    private Long closedQuestionId;

}
