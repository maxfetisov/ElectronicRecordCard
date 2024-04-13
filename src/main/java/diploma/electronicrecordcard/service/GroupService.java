package diploma.electronicrecordcard.service;


import diploma.electronicrecordcard.data.dto.model.GroupDto;

import java.util.List;

public interface GroupService {

    List<GroupDto> getAll();

    List<GroupDto> getByInstituteId(Short id);

    GroupDto getById(Integer id);

}
