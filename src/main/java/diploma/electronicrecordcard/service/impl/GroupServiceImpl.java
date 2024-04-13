package diploma.electronicrecordcard.service.impl;

import diploma.electronicrecordcard.data.dto.model.GroupDto;
import diploma.electronicrecordcard.data.entity.Group;
import diploma.electronicrecordcard.exception.entitynotfound.GroupNotFoundException;
import diploma.electronicrecordcard.repository.GroupRepository;
import diploma.electronicrecordcard.service.GroupService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    GroupRepository groupRepository;

    Mapper<GroupDto, Group> groupMapper;

    @Override
    public List<GroupDto> getAll() {
        return groupRepository.findAll().stream()
                .map(groupMapper::toDto)
                .toList();
    }

    @Override
    public List<GroupDto> getByInstituteId(Short id) {
        return groupRepository.findByInstituteId(id).stream()
                .map(groupMapper::toDto)
                .toList();
    }

    @Override
    public GroupDto getById(Integer id) {
        return groupMapper.toDto(groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException(id.toString())));
    }

}
