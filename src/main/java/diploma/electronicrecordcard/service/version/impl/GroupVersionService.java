package diploma.electronicrecordcard.service.version.impl;

import diploma.electronicrecordcard.data.dto.model.GroupDto;
import diploma.electronicrecordcard.data.entity.Group;
import diploma.electronicrecordcard.repository.VersionRepository;
import diploma.electronicrecordcard.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class GroupVersionService extends VersionServiceImpl<GroupDto, Group> {

    public GroupVersionService(VersionRepository<Group> repository, Mapper<GroupDto, Group> mapper) {
        super(repository, mapper);
    }

}
