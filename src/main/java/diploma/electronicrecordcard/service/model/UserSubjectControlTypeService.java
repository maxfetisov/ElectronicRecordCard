package diploma.electronicrecordcard.service.model;

import diploma.electronicrecordcard.data.dto.model.UserSubjectControlTypeDto;
import diploma.electronicrecordcard.data.dto.request.UserSubjectControlTypeCreateByGroupRequestDto;
import diploma.electronicrecordcard.data.dto.request.UserSubjectControlTypeCreateRequestDto;
import diploma.electronicrecordcard.service.criteria.CriteriaAndVersionService;

import java.util.List;

public interface UserSubjectControlTypeService extends CriteriaAndVersionService<UserSubjectControlTypeDto> {

    List<UserSubjectControlTypeDto> getAll();

    UserSubjectControlTypeDto create(UserSubjectControlTypeCreateRequestDto userSubjectControlTypeDto);

    List<UserSubjectControlTypeDto> create(UserSubjectControlTypeCreateByGroupRequestDto userSubjectControlTypeDto);

    UserSubjectControlTypeDto update(UserSubjectControlTypeDto userSubjectControlTypeDto);

    void delete(Long id, Long version);

}
