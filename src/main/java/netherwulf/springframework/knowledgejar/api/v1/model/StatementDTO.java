package netherwulf.springframework.knowledgejar.api.v1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class StatementDTO {
    private Long id;

    @NotBlank
    @Size(max = 250)
    private String content;

    @NotBlank
    private String isCorrect;

    private String statementUrl;

    private Long closedQuestionId;

    public String getIsCorrect() {
        return this.isCorrect;
    }

    public void setIsCorrect(String isCorrect) {
        this.isCorrect = isCorrect;
    }

}
