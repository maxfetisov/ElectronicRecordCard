package diploma.electronicrecordcard.service;

import diploma.electronicrecordcard.data.dto.model.InstituteDto;
import diploma.electronicrecordcard.data.dto.request.InstituteCreateRequestDto;

import java.util.List;

public interface InstituteService {

    List<InstituteDto> findAll();

    InstituteDto findById(Short id);

    InstituteDto create(InstituteCreateRequestDto instituteDto);

    InstituteDto update(InstituteDto instituteDto);

    void deleteById(Short id);

}
