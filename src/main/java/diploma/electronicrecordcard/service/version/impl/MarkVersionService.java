package diploma.electronicrecordcard.service.version.impl;

import diploma.electronicrecordcard.data.dto.model.MarkDto;
import diploma.electronicrecordcard.data.entity.Mark;
import diploma.electronicrecordcard.repository.VersionRepository;
import diploma.electronicrecordcard.service.account.AuthorityService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class MarkVersionService extends VersionServiceImpl<MarkDto, Mark> {

    public MarkVersionService(VersionRepository<Mark> repository, Mapper<MarkDto, Mark> mapper, AuthorityService authorityService) {
        super(repository, mapper, authorityService);
    }

}
