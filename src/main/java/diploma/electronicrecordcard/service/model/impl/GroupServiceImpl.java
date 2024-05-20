package diploma.electronicrecordcard.service.model.impl;

import diploma.electronicrecordcard.data.dto.model.GroupDto;
import diploma.electronicrecordcard.data.dto.request.GroupCreateRequestDto;
import diploma.electronicrecordcard.data.dto.request.GroupUpdateRequestDto;
import diploma.electronicrecordcard.data.entity.Group;
import diploma.electronicrecordcard.data.entity.Institute;
import diploma.electronicrecordcard.data.enumeration.RoleName;
import diploma.electronicrecordcard.exception.entitynotfound.GroupNotFoundException;
import diploma.electronicrecordcard.exception.entitynotfound.InstituteNotFoundException;
import diploma.electronicrecordcard.exception.noauthority.NoAuthorityException;
import diploma.electronicrecordcard.exception.versionconflict.GroupVersionConflictException;
import diploma.electronicrecordcard.repository.model.GroupRepository;
import diploma.electronicrecordcard.repository.model.InstituteRepository;
import diploma.electronicrecordcard.service.account.AuthorityService;
import diploma.electronicrecordcard.service.criteria.CriteriaService;
import diploma.electronicrecordcard.service.model.DeletionService;
import diploma.electronicrecordcard.service.model.GroupService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import diploma.electronicrecordcard.util.EntitySpecifications;
import diploma.electronicrecordcard.util.VersionUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static diploma.electronicrecordcard.data.enumeration.EntityType.GROUP;
import static java.util.Objects.nonNull;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    GroupRepository groupRepository;

    InstituteRepository instituteRepository;

    Mapper<GroupDto, Group> groupMapper;

    AuthorityService authorityService;

    CriteriaService<Group> groupCriteriaService;

    CriteriaService<Institute> instituteCriteriaService;

    DeletionService deletionService;

    @Override
    public List<GroupDto> getAll() {
        return getByCriteria(Map.of());
    }

    @Override
    public List<GroupDto> getByInstituteId(Short id) {
        return getByCriteria(Map.of("institute.id", id));
    }

    @Override
    public GroupDto getById(Integer id) {
        return getByCriteria(Map.of("id", id)).stream()
                .findFirst()
                .orElseThrow(() -> new GroupNotFoundException(id.toString()));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public GroupDto create(GroupCreateRequestDto groupDto) {
        authorityService.checkRolesAndThrow(List.of(RoleName.DEAN_OFFICE_EMPLOYEE, RoleName.ADMINISTRATOR));
        var currentUser = authorityService.getCurrentUser();
        var group = GroupDto.builder()
                .name(groupDto.name())
                .fullName(groupDto.fullName())
                .admissionDate(nonNull(groupDto.admissionDate()) ? groupDto.admissionDate() : LocalDate.now())
                .instituteId(nonNull(groupDto.instituteId()) ? groupDto.instituteId() : currentUser.instituteId())
                .deleted(false)
                .version(groupRepository.getNextVersion())
                .build();
        checkConstraints(group);
        return groupMapper.toDto(groupRepository.save(groupMapper.toEntity(group)));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public GroupDto update(GroupUpdateRequestDto groupDto) {
        authorityService.checkRolesAndThrow(List.of(RoleName.DEAN_OFFICE_EMPLOYEE, RoleName.ADMINISTRATOR));
        Group group = groupRepository.findById(groupDto.id())
                .orElseThrow(() -> new GroupNotFoundException(groupDto.id().toString()));
        VersionUtil.checkVersionAndThrowVersionConflict(group, groupDto, GroupVersionConflictException.class);
        this.checkRightsConstraint(groupMapper.toDto(group));
        var currentUser = authorityService.getCurrentUser();
        var updatedGroup = GroupDto.builder()
                .id(groupDto.id())
                .instituteId(nonNull(groupDto.instituteId()) ? groupDto.instituteId() : currentUser.instituteId())
                .admissionDate(nonNull(groupDto.admissionDate()) ? groupDto.admissionDate() : group.getAdmissionDate())
                .name(groupDto.name())
                .fullName(groupDto.fullName())
                .deleted(group.getDeleted())
                .version(groupRepository.getNextVersion())
                .build();
        checkConstraints(updatedGroup);
        return groupMapper.toDto(groupRepository.save(groupMapper.toEntity(updatedGroup)));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public GroupDto delete(Integer id, Long version) {
        authorityService.checkRolesAndThrow(List.of(RoleName.DEAN_OFFICE_EMPLOYEE, RoleName.ADMINISTRATOR));
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException(id.toString()));
        VersionUtil.checkVersionAndThrowVersionConflict(group, () -> version, GroupVersionConflictException.class);
        checkRightsConstraint(groupMapper.toDto(group));
        group.setDeleted(true);
        group.setVersion(groupRepository.getNextVersion());
        deletionService.create(GROUP, id.longValue());
        return groupMapper.toDto(groupRepository.save(group));
    }

    @Override
    public List<GroupDto> getByCriteria(Map<String, Object> criteria) {
        return groupCriteriaService.getByCriteria(EntitySpecifications.<Group>getSpecification(criteria)
                .orElse(null)).stream()
                .map(groupMapper::toDto)
                .toList();
    }

    @Override
    public List<GroupDto> getByCriteria(Map<String, Object> criteria, Long version) {
        var specification = EntitySpecifications.<Group>getSpecification(criteria);
        var versionSpecification = VersionUtil.<Group>getVersionSpecification(version);
        return groupCriteriaService.getByCriteria(specification.map((spec)
                -> spec.and(versionSpecification)).orElse(versionSpecification)).stream()
                .map(groupMapper::toDto)
                .toList();
    }

    private void checkConstraints(GroupDto group) {
        checkInstituteExistenceConstraint(group);
        checkRightsConstraint(group);
    }

    private void checkInstituteExistenceConstraint(GroupDto group) {
        var instituteList = instituteCriteriaService.getByCriteria(EntitySpecifications
                .<Institute>getSpecification(Map.of("id", group.instituteId()))
                .orElse(null));
        if(instituteList.isEmpty()) {
            throw new InstituteNotFoundException(group.instituteId().toString());
        }
    }

    private void checkRightsConstraint(GroupDto groupDto) {
        if(authorityService.hasAnyAuthority(List.of(RoleName.ADMINISTRATOR))) {
            return;
        }
        var currentUser = authorityService.getCurrentUser();
        if(!Objects.equals(currentUser.instituteId(), groupDto.instituteId())) {
            throw new NoAuthorityException();
        }
    }
}
