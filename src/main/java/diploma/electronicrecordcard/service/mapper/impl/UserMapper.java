package diploma.electronicrecordcard.service.mapper.impl;

import diploma.electronicrecordcard.data.dto.UserDto;
import diploma.electronicrecordcard.data.entity.Group;
import diploma.electronicrecordcard.data.entity.Institute;
import diploma.electronicrecordcard.data.entity.User;
import diploma.electronicrecordcard.service.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserDto, User> {

    @Override
    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .email(user.getEmail())
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .deleted(user.getDeleted())
                .phoneNumber(user.getPhoneNumber())
                .recordBookNumber(user.getRecordBookNumber())
                .groupId(user.getGroup().getId())
                .instituteId(user.getInstitute().getId())
                .build();
    }

    @Override
    public User toEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.id())
                .login(userDto.login())
                .email(userDto.email())
                .lastName(userDto.lastName())
                .firstName(userDto.firstName())
                .middleName(userDto.middleName())
                .deleted(userDto.deleted())
                .phoneNumber(userDto.phoneNumber())
                .recordBookNumber(userDto.recordBookNumber())
                .group(Group.builder().id(userDto.groupId()).build())
                .institute(Institute.builder().id(userDto.instituteId()).build())
                .build();
    }

}
