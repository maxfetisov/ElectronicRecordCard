package diploma.electronicrecordcard.service;

import diploma.electronicrecordcard.data.dto.model.ControlTypeDto;

import java.util.List;

public interface ControlTypeService {

    List<ControlTypeDto> getAll();

    ControlTypeDto getById(Short id);

    ControlTypeDto getByName(String name);

}
