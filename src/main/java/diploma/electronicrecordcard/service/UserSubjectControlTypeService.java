package diploma.electronicrecordcard.service;

import diploma.electronicrecordcard.data.dto.model.UserSubjectControlTypeDto;
import diploma.electronicrecordcard.data.dto.request.UserSubjectControlTypeCreateRequestDto;

import java.util.List;

public interface UserSubjectControlTypeService {

    List<UserSubjectControlTypeDto> getAll();

    List<UserSubjectControlTypeDto> getByCriteria(UserSubjectControlTypeDto criteria);

    UserSubjectControlTypeDto create(UserSubjectControlTypeCreateRequestDto userSubjectControlTypeDto);

    UserSubjectControlTypeDto update(UserSubjectControlTypeDto userSubjectControlTypeDto);

    void delete(Long id);

}
