package diploma.electronicrecordcard.service.model;

import diploma.electronicrecordcard.data.dto.model.StudentMarkDto;
import diploma.electronicrecordcard.data.dto.request.StudentMarkCreateRequestDto;
import diploma.electronicrecordcard.service.criteria.CriteriaService;

import java.util.List;

public interface StudentMarkService extends CriteriaService<StudentMarkDto> {

    List<StudentMarkDto> getAll();

    StudentMarkDto getById(Long id);

    StudentMarkDto create(StudentMarkCreateRequestDto studentMarkDto);

    StudentMarkDto update(StudentMarkDto studentMarkDto);

    void delete(Long id, Long version);
}
