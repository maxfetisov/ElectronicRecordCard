package diploma.electronicrecordcard.service.mapper.impl;

import diploma.electronicrecordcard.data.dto.StudentMarkDto;
import diploma.electronicrecordcard.data.entity.ControlType;
import diploma.electronicrecordcard.data.entity.Mark;
import diploma.electronicrecordcard.data.entity.StudentMark;
import diploma.electronicrecordcard.data.entity.Subject;
import diploma.electronicrecordcard.data.entity.User;
import diploma.electronicrecordcard.service.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class StudentMarkMapper implements Mapper<StudentMarkDto, StudentMark> {

    @Override
    public StudentMarkDto toDto(StudentMark studentMark) {
        return StudentMarkDto.builder()
                .id(studentMark.getId())
                .semester(studentMark.getSemester())
                .completionDate(studentMark.getCompletionDate())
                .hoursNumber(studentMark.getHoursNumber())
                .teacherId(studentMark.getTeacher().getId())
                .studentId(studentMark.getStudent().getId())
                .controlTypeId(studentMark.getControlType().getId())
                .markId(studentMark.getMark().getId())
                .subjectId(studentMark.getSubject().getId())
                .build();
    }

    @Override
    public StudentMark toEntity(StudentMarkDto studentMarkDto) {
        return StudentMark.builder()
                .id(studentMarkDto.id())
                .semester(studentMarkDto.semester())
                .completionDate(studentMarkDto.completionDate())
                .hoursNumber(studentMarkDto.hoursNumber())
                .teacher(User.builder().id(studentMarkDto.teacherId()).build())
                .student(User.builder().id(studentMarkDto.studentId()).build())
                .controlType(ControlType.builder().id(studentMarkDto.controlTypeId()).build())
                .mark(Mark.builder().id(studentMarkDto.markId()).build())
                .subject(Subject.builder().id(studentMarkDto.subjectId()).build())
                .build();
    }

}
