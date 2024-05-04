package diploma.electronicrecordcard.service.model.impl;

import diploma.electronicrecordcard.data.dto.model.RoleDto;
import diploma.electronicrecordcard.data.entity.Role;
import diploma.electronicrecordcard.repository.model.RoleRepository;
import diploma.electronicrecordcard.service.model.RoleService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    RoleRepository roleRepository;

    Mapper<RoleDto, Role> roleMapper;

    @Override
    public List<RoleDto> getAll() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toDto)
                .toList();
    }

}
