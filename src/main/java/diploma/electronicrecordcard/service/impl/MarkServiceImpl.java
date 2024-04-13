package diploma.electronicrecordcard.service.impl;

import diploma.electronicrecordcard.data.dto.model.MarkDto;
import diploma.electronicrecordcard.data.entity.Mark;
import diploma.electronicrecordcard.repository.MarkRepository;
import diploma.electronicrecordcard.service.MarkService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class MarkServiceImpl implements MarkService {

    MarkRepository markRepository;

    Mapper<MarkDto, Mark> markMapper;

    @Override
    public List<MarkDto> getByControlTypeId(Short id) {
        return markRepository.findByControlTypeId(id).stream()
                .map(markMapper::toDto)
                .toList();
    }

}
