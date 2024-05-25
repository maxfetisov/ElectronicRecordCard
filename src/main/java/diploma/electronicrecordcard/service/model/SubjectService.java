package diploma.electronicrecordcard.service.model;

import diploma.electronicrecordcard.data.dto.model.SubjectDto;
import diploma.electronicrecordcard.data.dto.request.SubjectCreateRequestDto;
import diploma.electronicrecordcard.service.criteria.CriteriaAndVersionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubjectService extends CriteriaAndVersionService<SubjectDto> {

    List<SubjectDto> getAll();

    Page<SubjectDto> getAll(Pageable pageable);

    SubjectDto getById(Long id);

    SubjectDto create(SubjectCreateRequestDto subjectDto);

    SubjectDto update(SubjectDto subjectDto);

    SubjectDto delete(Long id, Long version);

}
