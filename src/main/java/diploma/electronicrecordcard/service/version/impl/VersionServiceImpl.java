package diploma.electronicrecordcard.service.version.impl;

import diploma.electronicrecordcard.data.Versionable;
import diploma.electronicrecordcard.data.enumeration.RoleName;
import diploma.electronicrecordcard.repository.VersionRepository;
import diploma.electronicrecordcard.service.account.AuthorityService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import diploma.electronicrecordcard.service.version.VersionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class VersionServiceImpl<DTO, ENTITY extends Versionable> implements VersionService<DTO> {

    VersionRepository<ENTITY> repository;

    Mapper<DTO, ENTITY> mapper;

    AuthorityService authorityService;

    @Override
    public List<DTO> getByVersion(Long version) {
        authorityService.checkRolesAndThrow(List.of(RoleName.ADMINISTRATOR));
        return repository.findByVersionGreaterThan(version).stream()
                .map(mapper::toDto)
                .toList();
    }
}
