package diploma.electronicrecordcard.service.version.impl;

import diploma.electronicrecordcard.data.dto.model.SubjectDto;
import diploma.electronicrecordcard.data.entity.Subject;
import diploma.electronicrecordcard.repository.VersionRepository;
import diploma.electronicrecordcard.service.account.AuthorityService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class SubjectVersionService extends VersionServiceImpl<SubjectDto, Subject> {

    public SubjectVersionService(VersionRepository<Subject> repository, Mapper<SubjectDto, Subject> mapper, AuthorityService authorityService) {
        super(repository, mapper, authorityService);
    }

}
