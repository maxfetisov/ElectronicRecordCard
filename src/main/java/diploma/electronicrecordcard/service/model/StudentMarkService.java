package diploma.electronicrecordcard.service.model;

import diploma.electronicrecordcard.data.dto.model.StudentMarkDto;
import diploma.electronicrecordcard.data.dto.request.StudentMarkCreateRequestDto;
import diploma.electronicrecordcard.service.criteria.CriteriaAndVersionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentMarkService extends CriteriaAndVersionService<StudentMarkDto> {

    List<StudentMarkDto> getAll();

    Page<StudentMarkDto> getAll(Pageable pageable);

    StudentMarkDto getById(Long id);

    StudentMarkDto create(StudentMarkCreateRequestDto studentMarkDto);

    StudentMarkDto update(StudentMarkDto studentMarkDto);

    List<StudentMarkDto> createOrUpdate(List<StudentMarkDto> studentMarks);

    void delete(Long id, Long version);
}
