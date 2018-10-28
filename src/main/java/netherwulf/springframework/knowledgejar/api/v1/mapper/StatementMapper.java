package netherwulf.springframework.knowledgejar.api.v1.mapper;

import netherwulf.springframework.knowledgejar.api.v1.model.StatementDTO;
import netherwulf.springframework.knowledgejar.models.Statement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StatementMapper {
    StatementMapper INSTANCE = Mappers.getMapper(StatementMapper.class);

    StatementDTO statementToStatementDTO(Statement statement);

    Statement statementDTOToStatement(StatementDTO statementDTO);
}
