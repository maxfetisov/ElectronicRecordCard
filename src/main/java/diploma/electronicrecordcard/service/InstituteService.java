package diploma.electronicrecordcard.service;

import diploma.electronicrecordcard.data.dto.model.InstituteDto;

import java.util.List;

public interface InstituteService {

    List<InstituteDto> findAll();

    InstituteDto findById(Short id);

}
