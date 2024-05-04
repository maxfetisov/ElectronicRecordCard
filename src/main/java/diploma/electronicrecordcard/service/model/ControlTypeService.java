package diploma.electronicrecordcard.service.model;

import diploma.electronicrecordcard.data.dto.model.ControlTypeDto;
import diploma.electronicrecordcard.data.dto.request.ControlTypeUpdateRequestDto;
import diploma.electronicrecordcard.data.dto.response.ControlTypeMarkResponseDto;

import java.util.List;

public interface ControlTypeService {

    List<ControlTypeDto> getAll();

    List<ControlTypeMarkResponseDto> getAllWithMarks();

    ControlTypeDto getById(Short id);

    ControlTypeDto getByName(String name);

    ControlTypeDto update(ControlTypeUpdateRequestDto controlTypeDto);

}
