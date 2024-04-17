package diploma.electronicrecordcard.service;

import diploma.electronicrecordcard.data.dto.model.ControlTypeDto;
import diploma.electronicrecordcard.data.dto.request.ControlTypeUpdateRequestDto;

import java.util.List;

public interface ControlTypeService {

    List<ControlTypeDto> getAll();

    ControlTypeDto getById(Short id);

    ControlTypeDto getByName(String name);

    ControlTypeDto update(ControlTypeUpdateRequestDto controlTypeDto);

}
