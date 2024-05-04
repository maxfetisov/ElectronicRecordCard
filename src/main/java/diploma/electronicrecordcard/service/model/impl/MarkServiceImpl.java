package diploma.electronicrecordcard.service.model.impl;

import diploma.electronicrecordcard.data.dto.model.MarkDto;
import diploma.electronicrecordcard.data.dto.request.MarkUpdateRequestDto;
import diploma.electronicrecordcard.data.entity.Mark;
import diploma.electronicrecordcard.exception.entitynotfound.MarkNotFoundException;
import diploma.electronicrecordcard.exception.versionconflict.MarkVersionConflictException;
import diploma.electronicrecordcard.repository.model.MarkRepository;
import diploma.electronicrecordcard.service.model.MarkService;
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
public class MarkServiceImpl implements MarkService {

    MarkRepository markRepository;

    Mapper<MarkDto, Mark> markMapper;

    @Override
    public List<MarkDto> getAll() {
        return markRepository.findAll().stream()
                .map(markMapper::toDto)
                .toList();
    }

    @Override
    public List<MarkDto> getByControlTypeId(Short id) {
        return markRepository.findByControlTypeId(id).stream()
                .map(markMapper::toDto)
                .toList();
    }

    @Override
    public MarkDto getById(Short id) {
        return markMapper.toDto(markRepository.findById(id)
                .orElseThrow(() -> new MarkNotFoundException(id.toString())));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public MarkDto update(MarkUpdateRequestDto markDto) {
        Mark mark = markRepository.findById(markDto.id())
                .orElseThrow(() -> new MarkNotFoundException(markDto.id().toString()));
        VersionUtil.checkVersionAndThrowVersionConflict(mark,
                markDto,
                MarkVersionConflictException.class);
        mark.setTitle(markDto.title());
        mark.setVersion(markRepository.getNextVersion());
        return markMapper.toDto(markRepository.save(mark));
    }

}
