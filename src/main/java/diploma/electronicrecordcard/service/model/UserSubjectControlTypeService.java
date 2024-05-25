package diploma.electronicrecordcard.service.model;

import diploma.electronicrecordcard.data.dto.model.UserSubjectControlTypeDto;
import diploma.electronicrecordcard.data.dto.request.UserSubjectControlTypeCreateByGroupRequestDto;
import diploma.electronicrecordcard.data.dto.request.UserSubjectControlTypeCreateRequestDto;
import diploma.electronicrecordcard.service.criteria.CriteriaAndVersionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserSubjectControlTypeService extends CriteriaAndVersionService<UserSubjectControlTypeDto> {

    List<UserSubjectControlTypeDto> getAll();

    Page<UserSubjectControlTypeDto> getAll(Pageable pageable);

    UserSubjectControlTypeDto create(UserSubjectControlTypeCreateRequestDto userSubjectControlTypeDto);

    List<UserSubjectControlTypeDto> create(UserSubjectControlTypeCreateByGroupRequestDto userSubjectControlTypeDto);

    UserSubjectControlTypeDto update(UserSubjectControlTypeDto userSubjectControlTypeDto);

    void delete(Long id, Long version);

}
