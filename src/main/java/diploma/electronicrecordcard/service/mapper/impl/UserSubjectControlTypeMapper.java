package diploma.electronicrecordcard.service.mapper.impl;

import diploma.electronicrecordcard.data.dto.model.UserSubjectControlTypeDto;
import diploma.electronicrecordcard.data.entity.ControlType;
import diploma.electronicrecordcard.data.entity.Subject;
import diploma.electronicrecordcard.data.entity.User;
import diploma.electronicrecordcard.data.entity.UserSubjectControlType;
import diploma.electronicrecordcard.service.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserSubjectControlTypeMapper implements Mapper<UserSubjectControlTypeDto, UserSubjectControlType> {
    @Override
    public UserSubjectControlTypeDto toDto(UserSubjectControlType userSubjectControlType) {
        return UserSubjectControlTypeDto.builder()
                .id(userSubjectControlType.getId())
                .semester(userSubjectControlType.getSemester())
                .hoursNumber(userSubjectControlType.getHoursNumber())
                .teacherId(userSubjectControlType.getTeacher().getId())
                .studentId(userSubjectControlType.getStudent().getId())
                .controlTypeId(userSubjectControlType.getControlType().getId())
                .subjectId(userSubjectControlType.getSubject().getId())
                .version(userSubjectControlType.getVersion())
                .build();
    }

    @Override
    public UserSubjectControlType toEntity(UserSubjectControlTypeDto userSubjectControlTypeDto) {
        return UserSubjectControlType.builder()
                .id(userSubjectControlTypeDto.id())
                .semester(userSubjectControlTypeDto.semester())
                .hoursNumber(userSubjectControlTypeDto.hoursNumber())
                .teacher(User.builder().id(userSubjectControlTypeDto.teacherId()).build())
                .student(User.builder().id(userSubjectControlTypeDto.studentId()).build())
                .controlType(ControlType.builder().id(userSubjectControlTypeDto.controlTypeId()).build())
                .subject(Subject.builder().id(userSubjectControlTypeDto.subjectId()).build())
                .version(userSubjectControlTypeDto.getVersion())
                .build();
    }
}
