package diploma.electronicrecordcard.service.impl;

import diploma.electronicrecordcard.data.dto.model.ControlTypeDto;
import diploma.electronicrecordcard.data.dto.request.ControlTypeUpdateRequestDto;
import diploma.electronicrecordcard.data.entity.ControlType;
import diploma.electronicrecordcard.exception.entitynotfound.ControlTypeNotFoundException;
import diploma.electronicrecordcard.repository.ControlTypeRepository;
import diploma.electronicrecordcard.service.ControlTypeService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ControlTypeServiceImpl implements ControlTypeService {

    ControlTypeRepository controlTypeRepository;

    Mapper<ControlTypeDto, ControlType> controlTypeMapper;

    @Override
    public List<ControlTypeDto> getAll() {
        return controlTypeRepository.findAll().stream()
                .map(controlTypeMapper::toDto)
                .toList();
    }

    @Override
    public ControlTypeDto getById(Short id) {
        return controlTypeMapper.toDto(controlTypeRepository.findById(id)
                .orElseThrow(() -> new ControlTypeNotFoundException(id.toString())));
    }

    @Override
    public ControlTypeDto getByName(String name) {
        return controlTypeMapper.toDto(controlTypeRepository.findByName(name)
                .orElseThrow(() -> new ControlTypeNotFoundException("Название", name)));
    }

    @Override
    public ControlTypeDto update(ControlTypeUpdateRequestDto controlTypeDto) {
        ControlType controlType = controlTypeRepository.findById(controlTypeDto.id())
                .orElseThrow(() -> new ControlTypeNotFoundException(controlTypeDto.id().toString()));
        controlType.setTitle(controlTypeDto.title());
        return controlTypeMapper.toDto(controlTypeRepository.save(controlType));
    }
}
