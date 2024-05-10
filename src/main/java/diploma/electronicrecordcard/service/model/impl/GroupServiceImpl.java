package diploma.electronicrecordcard.service.model.impl;

import diploma.electronicrecordcard.data.dto.model.GroupDto;
import diploma.electronicrecordcard.data.dto.request.GroupCreateRequestDto;
import diploma.electronicrecordcard.data.dto.request.GroupUpdateRequestDto;
import diploma.electronicrecordcard.data.entity.Group;
import diploma.electronicrecordcard.data.entity.Institute;
import diploma.electronicrecordcard.exception.entitynotfound.GroupNotFoundException;
import diploma.electronicrecordcard.exception.entitynotfound.InstituteNotFoundException;
import diploma.electronicrecordcard.exception.versionconflict.GroupVersionConflictException;
import diploma.electronicrecordcard.repository.model.GroupRepository;
import diploma.electronicrecordcard.repository.model.InstituteRepository;
import diploma.electronicrecordcard.service.model.GroupService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import diploma.electronicrecordcard.util.EntitySpecifications;
import diploma.electronicrecordcard.util.VersionUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    GroupRepository groupRepository;

    InstituteRepository instituteRepository;

    Mapper<GroupDto, Group> groupMapper;

    @Override
    public List<GroupDto> getAll() {
        return groupRepository.findAll().stream()
                .map(groupMapper::toDto)
                .toList();
    }

    @Override
    public List<GroupDto> getByInstituteId(Short id) {
        return groupRepository.findByInstituteId(id).stream()
                .map(groupMapper::toDto)
                .toList();
    }

    @Override
    public GroupDto getById(Integer id) {
        return groupMapper.toDto(groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException(id.toString())));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public GroupDto create(GroupCreateRequestDto groupDto) {
        if(!instituteRepository.existsById(groupDto.instituteId())) {
            throw new InstituteNotFoundException(groupDto.instituteId().toString());
        }
        return groupMapper.toDto(groupRepository.save(groupMapper.toEntity(GroupDto.builder()
                .name(groupDto.name())
                .fullName(groupDto.fullName())
                .admissionDate(nonNull(groupDto.admissionDate()) ? groupDto.admissionDate() : LocalDate.now())
                .instituteId(groupDto.instituteId())
                .deleted(false)
                .version(groupRepository.getNextVersion())
                .build())));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public GroupDto update(GroupUpdateRequestDto groupDto) {
        Group group = groupRepository.findById(groupDto.id())
                .orElseThrow(() -> new GroupNotFoundException(groupDto.id().toString()));
        if(!instituteRepository.existsById(groupDto.instituteId())) {
            throw new InstituteNotFoundException(groupDto.instituteId().toString());
        }
        VersionUtil.checkVersionAndThrowVersionConflict(group, groupDto, GroupVersionConflictException.class);
        group.setName(groupDto.name());
        group.setFullName(groupDto.fullName());
        if(nonNull(groupDto.admissionDate())) {
            group.setAdmissionDate(groupDto.admissionDate());
        }
        group.setInstitute(Institute.builder().id(groupDto.instituteId()).build());
        group.setVersion(groupRepository.getNextVersion());
        return groupMapper.toDto(groupRepository.save(group));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public GroupDto delete(Integer id, Long version) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException(id.toString()));
        VersionUtil.checkVersionAndThrowVersionConflict(group, () -> version, GroupVersionConflictException.class);
        group.setDeleted(true);
        group.setVersion(groupRepository.getNextVersion());
        return groupMapper.toDto(groupRepository.save(group));
    }

    @Override
    public List<GroupDto> getByCriteria(Map<String, Object> criteria) {
        return getByCriteria(EntitySpecifications.getSpecification(criteria));
    }

    @Override
    public List<GroupDto> getByCriteria(Map<String, Object> criteria, Long version) {
        var specification = EntitySpecifications.<Group>getSpecification(criteria);
        var versionSpecification = VersionUtil.<Group>getVersionSpecification(version);
        return getByCriteria(Optional.of(specification.map((spec) -> spec.and(versionSpecification))
                .orElse(versionSpecification)));
    }

    private List<GroupDto> getByCriteria(Optional<Specification<Group>> specification) {
        return specification.map(groupRepository::findAll)
                .orElse(groupRepository.findAll())
                .stream()
                .map(groupMapper::toDto)
                .toList();
    }
}
