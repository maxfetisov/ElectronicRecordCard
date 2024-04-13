package diploma.electronicrecordcard.service;

import diploma.electronicrecordcard.data.dto.model.MarkDto;

import java.util.List;

public interface MarkService {

    List<MarkDto> getByControlTypeId(Short id);

}
