package diploma.electronicrecordcard.service;

import diploma.electronicrecordcard.data.dto.model.MarkDto;
import diploma.electronicrecordcard.data.dto.request.MarkUpdateRequestDto;

import java.util.List;

public interface MarkService {

    List<MarkDto> getAll();

    List<MarkDto> getByControlTypeId(Short id);

    MarkDto update(MarkUpdateRequestDto markDto);

}
