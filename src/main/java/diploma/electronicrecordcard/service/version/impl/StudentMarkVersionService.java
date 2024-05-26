package diploma.electronicrecordcard.service.version.impl;

import diploma.electronicrecordcard.data.dto.model.StudentMarkDto;
import diploma.electronicrecordcard.data.entity.StudentMark;
import diploma.electronicrecordcard.repository.VersionRepository;
import diploma.electronicrecordcard.service.account.AuthorityService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class StudentMarkVersionService extends VersionServiceImpl<StudentMarkDto, StudentMark> {

    public StudentMarkVersionService(VersionRepository<StudentMark> repository,
                                     Mapper<StudentMarkDto, StudentMark> mapper,
                                     AuthorityService authorityService) {
        super(repository, mapper, authorityService);
    }
}
