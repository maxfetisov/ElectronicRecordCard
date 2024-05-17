package diploma.electronicrecordcard.service.initialization.impl;

import diploma.electronicrecordcard.data.dto.model.RoleDto;
import diploma.electronicrecordcard.data.entity.Role;
import diploma.electronicrecordcard.data.entity.User;
import diploma.electronicrecordcard.data.enumeration.RoleName;
import diploma.electronicrecordcard.repository.model.UserRepository;
import diploma.electronicrecordcard.service.initialization.InitializationService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import diploma.electronicrecordcard.service.model.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class InitializationServiceImpl implements InitializationService {

    @NonFinal
    @Value("${admin.login}")
    String login;

    @NonFinal
    @Value("${admin.password}")
    String password;

    PasswordEncoder passwordEncoder;

    UserRepository userRepository;

    RoleService roleService;

    Mapper<RoleDto, Role> roleMapper;

    @Override
    public void createAdmin() {
        if (userRepository.existsByLogin(login)) {
            return;
        }
        userRepository.save(User.builder()
                .login(login)
                .password(passwordEncoder.encode(password))
                .lastName(login)
                .firstName(login)
                .deleted(false)
                .version(userRepository.getNextVersion())
                .roles(List.of(roleMapper.toEntity(roleService.getByName(RoleName.ADMINISTRATOR))))
                .build());
    }

}
