package diploma.electronicrecordcard.service.model;


import diploma.electronicrecordcard.data.dto.model.GroupDto;
import diploma.electronicrecordcard.data.dto.request.GroupCreateRequestDto;
import diploma.electronicrecordcard.data.dto.request.GroupUpdateRequestDto;
import diploma.electronicrecordcard.service.criteria.CriteriaAndVersionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GroupService extends CriteriaAndVersionService<GroupDto> {

    List<GroupDto> getAll();

    Page<GroupDto> getAll(Pageable pageable);

    List<GroupDto> getByInstituteId(Short id);

    GroupDto getById(Integer id);

    GroupDto create(GroupCreateRequestDto groupDto);

    GroupDto update(GroupUpdateRequestDto groupDto);

    GroupDto delete(Integer id, Long version);

}
