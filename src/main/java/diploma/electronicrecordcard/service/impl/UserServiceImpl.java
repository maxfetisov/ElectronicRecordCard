package diploma.electronicrecordcard.service.impl;

import diploma.electronicrecordcard.data.dto.model.RoleDto;
import diploma.electronicrecordcard.data.dto.model.UserDto;
import diploma.electronicrecordcard.data.dto.request.UserCreateRequestDto;
import diploma.electronicrecordcard.data.entity.Role;
import diploma.electronicrecordcard.data.entity.User;
import diploma.electronicrecordcard.repository.UserRepository;
import diploma.electronicrecordcard.service.UserService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
    public UserDto createUser(UserCreateRequestDto userDto) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username);
    }
}
