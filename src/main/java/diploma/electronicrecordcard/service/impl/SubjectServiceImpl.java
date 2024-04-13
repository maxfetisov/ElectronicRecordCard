package diploma.electronicrecordcard.service.impl;

import diploma.electronicrecordcard.data.dto.model.SubjectDto;
import diploma.electronicrecordcard.data.entity.Subject;
import diploma.electronicrecordcard.exception.entitynotfound.SubjectNotFoundException;
import diploma.electronicrecordcard.repository.SubjectRepository;
import diploma.electronicrecordcard.service.SubjectService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

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

}
