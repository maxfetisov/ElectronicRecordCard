package diploma.electronicrecordcard.service.model;

import diploma.electronicrecordcard.data.dto.model.InstituteDto;
import diploma.electronicrecordcard.data.dto.request.InstituteCreateRequestDto;
import diploma.electronicrecordcard.service.criteria.CriteriaService;

import java.util.List;

public interface InstituteService extends CriteriaService<InstituteDto> {

    List<InstituteDto> findAll();

    InstituteDto findById(Short id);

    InstituteDto create(InstituteCreateRequestDto instituteDto);

    InstituteDto update(InstituteDto instituteDto);

    void delete(Short id);

}
