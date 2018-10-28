package netherwulf.springframework.knowledgejar.api.v1.mapper;

import netherwulf.springframework.knowledgejar.api.v1.model.SubchapterDTO;
import netherwulf.springframework.knowledgejar.models.Subchapter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubchapterMapper {
    SubchapterMapper INSTANCE = Mappers.getMapper(SubchapterMapper.class);

    SubchapterDTO subchapterToSubchapterDTO(Subchapter subchapter);

    Subchapter subchapterDTOToSubchapter(SubchapterDTO subchapterDTO);
}
