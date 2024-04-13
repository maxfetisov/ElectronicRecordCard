package diploma.electronicrecordcard.service.mapper.impl;

import diploma.electronicrecordcard.data.dto.ControlTypeDto;
import diploma.electronicrecordcard.data.entity.ControlType;
import diploma.electronicrecordcard.service.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class ControlTypeMapper implements Mapper<ControlTypeDto, ControlType> {

    @Override
    public ControlTypeDto toDto(ControlType controlType) {
        return ControlTypeDto.builder()
                .id(controlType.getId())
                .name(controlType.getName())
                .build();
    }

    @Override
    public ControlType toEntity(ControlTypeDto controlTypeDto) {
        return ControlType.builder()
                .id(controlTypeDto.getId())
                .name(controlTypeDto.getName())
                .build();
    }

}
