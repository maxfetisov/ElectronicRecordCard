package diploma.electronicrecordcard.service.version.impl;

import diploma.electronicrecordcard.data.dto.model.UserDto;
import diploma.electronicrecordcard.data.entity.User;
import diploma.electronicrecordcard.repository.VersionRepository;
import diploma.electronicrecordcard.service.account.AuthorityService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class UserVersionService extends VersionServiceImpl<UserDto, User> {

    public UserVersionService(VersionRepository<User> repository,
                              Mapper<UserDto, User> mapper,
                              AuthorityService authorityService) {
        super(repository, mapper, authorityService);
    }

}
