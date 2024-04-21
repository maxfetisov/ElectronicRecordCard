package diploma.electronicrecordcard.service.impl;

import diploma.electronicrecordcard.data.dto.model.RoleDto;
import diploma.electronicrecordcard.data.dto.model.UserDto;
import diploma.electronicrecordcard.data.dto.request.UserUpdateRequestDto;
import diploma.electronicrecordcard.data.entity.Role;
import diploma.electronicrecordcard.data.entity.User;
import diploma.electronicrecordcard.exception.entityalreadyexists.UserAlreadyExistsException;
import diploma.electronicrecordcard.exception.entitynotfound.UserNotFoundException;
import diploma.electronicrecordcard.repository.UserRepository;
import diploma.electronicrecordcard.service.UserService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    Mapper<UserDto, User> userMapper;

    Mapper<RoleDto, Role> roleMapper;

    @Override
    public UserDto getByLogin(String login) {
        return userMapper.toDto(userRepository.findByLogin(login));
    }

    @Override
    public List<RoleDto> getUserRoles(Long id) {
        return userRepository.findRolesByUserId(id).stream()
                .map(roleMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public UserDto create(UserDto userDto) {
        if (userRepository.existsByLogin(userDto.login())) {
            throw new UserAlreadyExistsException(userDto.login());
        }
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
    }

    @Override
    @Transactional
    public UserDto update(UserUpdateRequestDto userDto) {
        User user = userRepository.findById(userDto.id())
                .orElseThrow(() -> new UserNotFoundException(userDto.id().toString()));
        if (!user.getLogin().equals(userDto.login()) && userRepository.existsByLogin(userDto.login())) {
            throw new UserAlreadyExistsException(userDto.login());
        }

        return userMapper.toDto(userRepository.save(userMapper.toEntity(UserDto.builder()
                .id(userDto.id())
                .login(userDto.login())
                .password(user.getPassword())
                .lastName(userDto.lastName())
                .firstName(userDto.firstName())
                .middleName(userDto.middleName())
                .phoneNumber(userDto.phoneNumber())
                .email(userDto.email())
                .recordBookNumber(userDto.recordBookNumber())
                .groupId(userDto.groupId())
                .instituteId(userDto.instituteId())
                .roles(userDto.roles())
                .deleted(user.getDeleted())
                .build())));
    }

    @Override
    @Transactional
    public UserDto delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(id.toString()));
        user.setDeleted(true);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }
}
