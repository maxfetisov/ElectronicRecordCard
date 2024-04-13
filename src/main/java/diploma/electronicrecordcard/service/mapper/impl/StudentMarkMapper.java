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
                .id(studentMarkDto.getId())
                .semester(studentMarkDto.getSemester())
                .completionDate(studentMarkDto.getCompletionDate())
                .hoursNumber(studentMarkDto.getHoursNumber())
                .teacher(User.builder().id(studentMarkDto.getTeacherId()).build())
                .student(User.builder().id(studentMarkDto.getStudentId()).build())
                .controlType(ControlType.builder().id(studentMarkDto.getControlTypeId()).build())
                .mark(Mark.builder().id(studentMarkDto.getMarkId()).build())
                .subject(Subject.builder().id(studentMarkDto.getSubjectId()).build())
                .build();
    }

}
