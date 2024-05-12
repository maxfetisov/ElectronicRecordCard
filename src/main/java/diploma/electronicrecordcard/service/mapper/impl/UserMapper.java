package diploma.electronicrecordcard.service.mapper.impl;

import diploma.electronicrecordcard.data.dto.model.UserDto;
import diploma.electronicrecordcard.data.entity.Group;
import diploma.electronicrecordcard.data.entity.Institute;
import diploma.electronicrecordcard.data.entity.Role;
import diploma.electronicrecordcard.data.entity.User;
import diploma.electronicrecordcard.service.mapper.Mapper;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
public class UserMapper implements Mapper<UserDto, User> {

    @Override
    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .email(user.getEmail())
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .deleted(user.getDeleted())
                .phoneNumber(user.getPhoneNumber())
                .recordBookNumber(user.getRecordBookNumber())
                .groupId(nonNull(user.getGroup()) ? user.getGroup().getId() : null)
                .instituteId(nonNull(user.getInstitute()) ? user.getInstitute().getId() : null)
                .roles(user.getRoles().stream().map(Role::getId).toList())
                .version(user.getVersion())
                .build();
    }

    @Override
    public User toEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.id())
                .login(userDto.login())
                .password(userDto.password())
                .email(userDto.email())
                .lastName(userDto.lastName())
                .firstName(userDto.firstName())
                .middleName(userDto.middleName())
                .deleted(userDto.deleted())
                .phoneNumber(userDto.phoneNumber())
                .recordBookNumber(userDto.recordBookNumber())
                .group(nonNull(userDto.groupId()) ? Group.builder().id(userDto.groupId()).build() : null)
                .institute(nonNull(userDto.instituteId())
                        ? Institute.builder().id(userDto.instituteId()).build()
                        : null)
                .roles(userDto.roles().stream().map(role -> Role.builder().id(role).build()).toList())
                .version(userDto.getVersion())
                .build();
    }

}
