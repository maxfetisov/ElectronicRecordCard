package diploma.electronicrecordcard.service.impl;

import diploma.electronicrecordcard.data.dto.model.RoleDto;
import diploma.electronicrecordcard.repository.UserRepository;
import diploma.electronicrecordcard.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public List<RoleDto> getUserRoles(Long id) {
        return null;
    }
}
