package diploma.electronicrecordcard.service.mapper.impl;

import diploma.electronicrecordcard.data.dto.model.SubjectDto;
import diploma.electronicrecordcard.data.entity.Subject;
import diploma.electronicrecordcard.service.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper implements Mapper<SubjectDto, Subject> {

    @Override
    public SubjectDto toDto(Subject subject) {
        return SubjectDto.builder()
                .id(subject.getId())
                .name(subject.getName())
                .build();
    }

    @Override
    public Subject toEntity(SubjectDto subjectDto) {
        return Subject.builder()
                .id(subjectDto.id())
                .name(subjectDto.name())
                .build();
    }

}
