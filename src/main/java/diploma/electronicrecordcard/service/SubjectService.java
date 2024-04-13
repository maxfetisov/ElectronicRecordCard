package diploma.electronicrecordcard.service;

import diploma.electronicrecordcard.data.dto.model.SubjectDto;

import java.util.List;

public interface SubjectService {

    List<SubjectDto> getAll();

    SubjectDto getById(Long id);

}
