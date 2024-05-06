package diploma.electronicrecordcard.service.model.impl;

import diploma.electronicrecordcard.data.dto.model.StudentMarkDto;
import diploma.electronicrecordcard.data.dto.request.StudentMarkCreateRequestDto;
import diploma.electronicrecordcard.data.entity.StudentMark;
import diploma.electronicrecordcard.exception.entitynotfound.StudentMarkNotFoundException;
import diploma.electronicrecordcard.exception.versionconflict.StudentMarkVersionConflictException;
import diploma.electronicrecordcard.repository.model.StudentMarkRepository;
import diploma.electronicrecordcard.service.mapper.Mapper;
import diploma.electronicrecordcard.service.model.StudentMarkService;
import diploma.electronicrecordcard.util.EntitySpecifications;
import diploma.electronicrecordcard.util.VersionUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class StudentMarkServiceImpl implements StudentMarkService {

    StudentMarkRepository studentMarkRepository;

    Mapper<StudentMarkDto, StudentMark> studentMarkMapper;

    @Override
    public List<StudentMarkDto> getAll() {
        return studentMarkRepository.findAll().stream()
                .map(studentMarkMapper::toDto)
                .toList();
    }

    @Override
    public StudentMarkDto getById(Long id) {
        return studentMarkMapper.toDto(studentMarkRepository.findById(id)
                .orElseThrow(() -> new StudentMarkNotFoundException(id.toString())));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public StudentMarkDto create(StudentMarkCreateRequestDto studentMarkDto) {
        return studentMarkMapper.toDto(studentMarkRepository.save(studentMarkMapper.toEntity(StudentMarkDto.builder()
                .completionDate(studentMarkDto.completionDate())
                .markId(studentMarkDto.markId())
                .userSubjectControlTypeId(studentMarkDto.userSubjectControlTypeId())
                .version(studentMarkRepository.getNextVersion())
                .build())));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public StudentMarkDto update(StudentMarkDto studentMarkDto) {
        StudentMark studentMark = studentMarkRepository.findById(studentMarkDto.id())
                .orElseThrow(() -> new StudentMarkNotFoundException(studentMarkDto.id().toString()));
        VersionUtil.checkVersionAndThrowVersionConflict(studentMark,
                studentMarkDto,
                StudentMarkVersionConflictException.class);
        StudentMark newStudentMark = studentMarkMapper.toEntity(studentMarkDto);
        newStudentMark.setVersion(studentMarkRepository.getNextVersion());
        return studentMarkMapper.toDto(studentMarkRepository.save(newStudentMark));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(!studentMarkRepository.existsById(id)) {
            throw new StudentMarkNotFoundException(id.toString());
        }
        studentMarkRepository.deleteById(id);
    }

    @Override
    public List<StudentMarkDto> getByCriteria(Map<String, Object> criteria) {
        return getByCriteria(EntitySpecifications.getSpecification(criteria));
    }

    @Override
    public List<StudentMarkDto> getByCriteria(Map<String, Object> criteria, Long version) {
        var specification = EntitySpecifications.<StudentMark>getSpecification(criteria);
        var versionSpecification = VersionUtil.<StudentMark>getVersionSpecification(version);
        return getByCriteria(Optional.of(specification.map((spec) -> spec.and(versionSpecification))
                .orElse(versionSpecification)));
    }

    private List<StudentMarkDto> getByCriteria(Optional<Specification<StudentMark>> specification) {
        return specification.map(studentMarkRepository::findAll)
                .orElse(studentMarkRepository.findAll())
                .stream()
                .map(studentMarkMapper::toDto)
                .toList();
    }

}
