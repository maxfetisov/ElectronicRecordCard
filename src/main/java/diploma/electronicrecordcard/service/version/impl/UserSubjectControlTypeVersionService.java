package diploma.electronicrecordcard.service.version.impl;

import diploma.electronicrecordcard.data.dto.model.UserSubjectControlTypeDto;
import diploma.electronicrecordcard.data.entity.UserSubjectControlType;
import diploma.electronicrecordcard.repository.VersionRepository;
import diploma.electronicrecordcard.service.account.AuthorityService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class UserSubjectControlTypeVersionService
        extends VersionServiceImpl<UserSubjectControlTypeDto, UserSubjectControlType> {

    public UserSubjectControlTypeVersionService(VersionRepository<UserSubjectControlType> repository,
                                                Mapper<UserSubjectControlTypeDto, UserSubjectControlType> mapper,
                                                AuthorityService authorityService) {
        super(repository, mapper, authorityService);
    }

}
