package diploma.electronicrecordcard.service.version.impl;

import diploma.electronicrecordcard.data.dto.model.DeletionDto;
import diploma.electronicrecordcard.data.entity.Deletion;
import diploma.electronicrecordcard.repository.VersionRepository;
import diploma.electronicrecordcard.service.account.AuthorityService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class DeletionVersionService extends VersionServiceImpl<DeletionDto, Deletion> {

    public DeletionVersionService(VersionRepository<Deletion> repository,
                                  Mapper<DeletionDto, Deletion> mapper,
                                  AuthorityService authorityService) {
        super(repository, mapper, authorityService);
    }

}
