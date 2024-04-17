package diploma.electronicrecordcard.service;

import diploma.electronicrecordcard.data.dto.model.SubjectDto;
import diploma.electronicrecordcard.data.dto.request.SubjectCreateRequestDto;

import java.util.List;

public interface SubjectService {

    List<SubjectDto> getAll();

    SubjectDto getById(Long id);

    SubjectDto create(SubjectCreateRequestDto subjectDto);

    SubjectDto update(SubjectDto subjectDto);

    void deleteById(Long id);

}
