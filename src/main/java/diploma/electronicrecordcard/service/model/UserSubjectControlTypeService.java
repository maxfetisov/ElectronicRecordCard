package diploma.electronicrecordcard.service.model;

import diploma.electronicrecordcard.data.dto.model.UserSubjectControlTypeDto;
import diploma.electronicrecordcard.data.dto.request.UserSubjectControlTypeCreateRequestDto;

import java.util.List;
import java.util.Map;

public interface UserSubjectControlTypeService {

    List<UserSubjectControlTypeDto> getAll();

    List<UserSubjectControlTypeDto> getByCriteria(UserSubjectControlTypeDto criteria);

    List<UserSubjectControlTypeDto> getByCriteria(Map<String, Object> criteria);

    UserSubjectControlTypeDto create(UserSubjectControlTypeCreateRequestDto userSubjectControlTypeDto);

    UserSubjectControlTypeDto update(UserSubjectControlTypeDto userSubjectControlTypeDto);

    void delete(Long id);

}
