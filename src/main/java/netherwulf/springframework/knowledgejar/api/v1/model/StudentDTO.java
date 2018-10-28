package netherwulf.springframework.knowledgejar.api.v1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class StudentDTO {
    private Long id;

    @NotBlank
    @Size(max = 30)
    private String login;

    @NotBlank
    @Size(max = 20)
    private String password;

    @Size(max = 20)
    private String name;

    @Size(max = 30)
    private String surname;

    @Min(0)
    @Max(100)
    private Integer progress;

    @Size(max = 30)
    private String email;

    @Size(max = 30)
    private String joinDate;

    private String studentUrl;

    private Set<ChapterDTO> chapters = new HashSet<>();
    private Set<AnswerDTO> answers = new HashSet<>();
}
