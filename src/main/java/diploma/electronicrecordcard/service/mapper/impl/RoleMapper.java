package diploma.electronicrecordcard.service.mapper.impl;

import diploma.electronicrecordcard.data.dto.model.RoleDto;
import diploma.electronicrecordcard.data.entity.Role;
import diploma.electronicrecordcard.service.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements Mapper<RoleDto, Role> {

    @Override
    public RoleDto toDto(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    @Override
    public Role toEntity(RoleDto roleDto) {
        return Role.builder()
                .id(roleDto.id())
                .name(roleDto.name())
                .build();
    }

}
