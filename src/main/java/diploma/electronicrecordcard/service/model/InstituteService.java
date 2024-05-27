package diploma.electronicrecordcard.service.model;

import diploma.electronicrecordcard.data.dto.model.InstituteDto;
import diploma.electronicrecordcard.data.dto.request.InstituteCreateRequestDto;
import diploma.electronicrecordcard.data.dto.request.InstituteUpdateRequestDto;
import diploma.electronicrecordcard.service.criteria.CriteriaAndVersionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InstituteService extends CriteriaAndVersionService<InstituteDto> {

    List<InstituteDto> getAll();

    Page<InstituteDto> getAll(Pageable pageable);

    InstituteDto getById(Short id);

    InstituteDto create(InstituteCreateRequestDto instituteDto);

    InstituteDto update(InstituteUpdateRequestDto instituteDto);

    InstituteDto delete(Short id, Long version);

}
