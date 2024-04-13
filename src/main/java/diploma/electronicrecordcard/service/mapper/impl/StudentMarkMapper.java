package diploma.electronicrecordcard.service.mapper.impl;

import diploma.electronicrecordcard.data.dto.model.StudentMarkDto;
import diploma.electronicrecordcard.data.entity.ControlType;
import diploma.electronicrecordcard.data.entity.Mark;
import diploma.electronicrecordcard.data.entity.StudentMark;
import diploma.electronicrecordcard.data.entity.Subject;
import diploma.electronicrecordcard.data.entity.User;
import diploma.electronicrecordcard.data.entity.UserSubjectControlType;
import diploma.electronicrecordcard.service.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class StudentMarkMapper implements Mapper<StudentMarkDto, StudentMark> {

    @Override
    public StudentMarkDto toDto(StudentMark studentMark) {
        return StudentMarkDto.builder()
                .id(studentMark.getId())
                .completionDate(studentMark.getCompletionDate())
                .markId(studentMark.getMark().getId())
                .userSubjectControlTypeId(studentMark.getUserSubjectControlType().getId())
                .build();
    }

    @Override
    public StudentMark toEntity(StudentMarkDto studentMarkDto) {
        return StudentMark.builder()
                .id(studentMarkDto.id())
                .completionDate(studentMarkDto.completionDate())
                .mark(Mark.builder().id(studentMarkDto.markId()).build())
                .userSubjectControlType(UserSubjectControlType.builder().id(studentMarkDto.id()).build())
                .build();
    }

}
