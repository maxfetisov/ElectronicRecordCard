package diploma.electronicrecordcard.service.model;


import diploma.electronicrecordcard.data.dto.model.GroupDto;
import diploma.electronicrecordcard.data.dto.request.GroupCreateRequestDto;
import diploma.electronicrecordcard.data.dto.request.GroupUpdateRequestDto;
import diploma.electronicrecordcard.service.criteria.CriteriaService;

import java.util.List;

public interface GroupService extends CriteriaService<GroupDto> {

    List<GroupDto> getAll();

    List<GroupDto> getByInstituteId(Short id);

    GroupDto getById(Integer id);

    GroupDto create(GroupCreateRequestDto groupDto);

    GroupDto update(GroupUpdateRequestDto groupDto);

    GroupDto delete(Integer id);

}
