package diploma.electronicrecordcard.service.model;

import diploma.electronicrecordcard.data.dto.model.SubjectDto;
import diploma.electronicrecordcard.data.dto.request.SubjectCreateRequestDto;
import diploma.electronicrecordcard.service.criteria.CriteriaService;

import java.util.List;

public interface SubjectService extends CriteriaService<SubjectDto> {

    List<SubjectDto> getAll();

    SubjectDto getById(Long id);

    SubjectDto create(SubjectCreateRequestDto subjectDto);

    SubjectDto update(SubjectDto subjectDto);

    void delete(Long id);

}
