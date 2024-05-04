package diploma.electronicrecordcard.service.model.impl;

import diploma.electronicrecordcard.data.dto.model.SubjectDto;
import diploma.electronicrecordcard.data.dto.request.SubjectCreateRequestDto;
import diploma.electronicrecordcard.data.entity.Subject;
import diploma.electronicrecordcard.exception.entitynotfound.SubjectNotFoundException;
import diploma.electronicrecordcard.exception.versionconflict.SubjectVersionConflictException;
import diploma.electronicrecordcard.repository.model.SubjectRepository;
import diploma.electronicrecordcard.service.model.SubjectService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import diploma.electronicrecordcard.util.VersionUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    SubjectRepository subjectRepository;

    Mapper<SubjectDto, Subject> subjectMapper;

    @Override
    public List<SubjectDto> getAll() {
        return subjectRepository.findAll().stream()
                .map(subjectMapper::toDto)
                .toList();
    }

    @Override
    public SubjectDto getById(Long id) {
        return subjectMapper.toDto(subjectRepository.findById(id)
                .orElseThrow(() -> new SubjectNotFoundException(id.toString())));
    }

    @Override
    @Transactional
    public SubjectDto create(SubjectCreateRequestDto subjectDto) {
        return subjectMapper.toDto(subjectRepository.save(subjectMapper.toEntity(SubjectDto.builder()
                .name(subjectDto.name())
                .version(subjectRepository.getNextVersion())
                .build())));
    }

    @Override
    @Transactional
    public SubjectDto update(SubjectDto subjectDto) {
        Subject subject = subjectRepository.findById(subjectDto.id())
                .orElseThrow(() -> new SubjectNotFoundException(subjectDto.id().toString()));
        VersionUtil.checkVersionAndThrowVersionConflict(subject, subjectDto, SubjectVersionConflictException.class);
        Subject newSubject = subjectMapper.toEntity(subjectDto);
        newSubject.setVersion(subjectRepository.getNextVersion());
        return subjectMapper.toDto(subjectRepository.save(newSubject));
    }

    @Override
    public void deleteById(Long id) {
        if(!subjectRepository.existsById(id)) {
            throw new SubjectNotFoundException(id.toString());
        }
        subjectRepository.deleteById(id);
    }

}
