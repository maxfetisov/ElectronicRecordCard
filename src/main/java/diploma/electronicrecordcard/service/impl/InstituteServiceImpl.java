package diploma.electronicrecordcard.service.impl;

import diploma.electronicrecordcard.data.dto.model.InstituteDto;
import diploma.electronicrecordcard.data.entity.Institute;
import diploma.electronicrecordcard.exception.entitynotfound.InstituteNotFoundException;
import diploma.electronicrecordcard.repository.InstituteRepository;
import diploma.electronicrecordcard.service.InstituteService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class InstituteServiceImpl implements InstituteService {

    InstituteRepository instituteRepository;

    Mapper<InstituteDto, Institute> instituteMapper;

    @Override
    public List<InstituteDto> findAll() {
        return instituteRepository.findAll().stream()
                .map(instituteMapper::toDto)
                .toList();
    }

    @Override
    public InstituteDto findById(Short id) {
        return instituteMapper.toDto(instituteRepository.findById(id)
                .orElseThrow(() -> new InstituteNotFoundException(id.toString())));
    }

}
