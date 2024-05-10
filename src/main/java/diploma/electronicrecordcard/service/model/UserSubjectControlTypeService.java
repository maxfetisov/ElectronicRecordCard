package diploma.electronicrecordcard.service.model;

import diploma.electronicrecordcard.data.dto.model.UserSubjectControlTypeDto;
import diploma.electronicrecordcard.data.dto.request.UserSubjectControlTypeCreateRequestDto;
import diploma.electronicrecordcard.service.criteria.CriteriaService;

import java.util.List;

public interface UserSubjectControlTypeService extends CriteriaService<UserSubjectControlTypeDto> {

    List<UserSubjectControlTypeDto> getAll();

    @Deprecated
    List<UserSubjectControlTypeDto> getByCriteria(UserSubjectControlTypeDto criteria);

    UserSubjectControlTypeDto create(UserSubjectControlTypeCreateRequestDto userSubjectControlTypeDto);

    UserSubjectControlTypeDto update(UserSubjectControlTypeDto userSubjectControlTypeDto);

    void delete(Long id, Long version);

}
