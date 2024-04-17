package diploma.electronicrecordcard.service;


import diploma.electronicrecordcard.data.dto.model.GroupDto;
import diploma.electronicrecordcard.data.dto.request.GroupCreateRequestDto;
import diploma.electronicrecordcard.data.dto.request.GroupUpdateRequestDto;

import java.util.List;

public interface GroupService {

    List<GroupDto> getAll();

    List<GroupDto> getByInstituteId(Short id);

    GroupDto getById(Integer id);

    GroupDto create(GroupCreateRequestDto groupDto);

    GroupDto update(GroupUpdateRequestDto groupDto);

    GroupDto delete(Integer id);

}
