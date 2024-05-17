package diploma.electronicrecordcard.service.model.impl;

import diploma.electronicrecordcard.data.dto.model.RoleDto;
import diploma.electronicrecordcard.data.entity.Role;
import diploma.electronicrecordcard.data.enumeration.RoleName;
import diploma.electronicrecordcard.exception.entitynotfound.RoleNotFoundException;
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

    @Override
    public RoleDto getByName(RoleName name) {
        return roleRepository.findByName(name.name())
                .map(roleMapper::toDto)
                .orElseThrow(() -> new RoleNotFoundException("name", name.name()));
    }

}
