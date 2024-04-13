package diploma.electronicrecordcard.service.mapper.impl;

import diploma.electronicrecordcard.data.dto.MarkDto;
import diploma.electronicrecordcard.data.entity.Mark;
import diploma.electronicrecordcard.service.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class MarkMapper implements Mapper<MarkDto, Mark> {

    @Override
    public MarkDto toDto(Mark mark) {
        return MarkDto.builder()
                .id(mark.getId())
                .name(mark.getName())
                .title(mark.getTitle())
                .value(mark.getValue())
                .build();
    }

    @Override
    public Mark toEntity(MarkDto markDto) {
        return Mark.builder()
                .id(markDto.id())
                .name(markDto.name())
                .title(markDto.title())
                .value(markDto.value())
                .build();
    }

}
