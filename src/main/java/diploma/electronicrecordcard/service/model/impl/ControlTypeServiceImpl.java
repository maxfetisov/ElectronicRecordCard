package diploma.electronicrecordcard.service.model.impl;


import diploma.electronicrecordcard.data.dto.model.ControlTypeDto;
import diploma.electronicrecordcard.data.dto.request.ControlTypeUpdateRequestDto;
import diploma.electronicrecordcard.data.dto.response.ControlTypeMarkResponseDto;
import diploma.electronicrecordcard.data.entity.ControlType;
import diploma.electronicrecordcard.data.entity.Mark;
import diploma.electronicrecordcard.exception.entitynotfound.ControlTypeNotFoundException;
import diploma.electronicrecordcard.exception.versionconflict.ControlTypeVersionConflictException;
import diploma.electronicrecordcard.repository.model.ControlTypeRepository;
import diploma.electronicrecordcard.service.model.ControlTypeService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import diploma.electronicrecordcard.util.VersionUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

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
    public List<ControlTypeMarkResponseDto> getAllWithMarks() {
        List<ControlType> controlTypes = controlTypeRepository.findAll();
        return controlTypes.stream()
                .map(controlType -> ControlTypeMarkResponseDto.builder()
                        .controlTypeId(controlType.getId())
                        .markIds(controlType.getMarks().stream().map(Mark::getId).toList())
                        .build())
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
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public ControlTypeDto update(ControlTypeUpdateRequestDto controlTypeDto) {
        ControlType controlType = controlTypeRepository.findById(controlTypeDto.id())
                .orElseThrow(() -> new ControlTypeNotFoundException(controlTypeDto.id().toString()));
        VersionUtil.checkVersionAndThrowVersionConflict(
                controlType,
                controlTypeDto,
                ControlTypeVersionConflictException.class);
        controlType.setTitle(controlTypeDto.title());
        controlType.setVersion(controlTypeRepository.getNextVersion());
        return controlTypeMapper.toDto(controlTypeRepository.save(controlType));
    }
}
