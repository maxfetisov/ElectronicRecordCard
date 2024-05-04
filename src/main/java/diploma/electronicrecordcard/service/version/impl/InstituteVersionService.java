package diploma.electronicrecordcard.service.version.impl;

import diploma.electronicrecordcard.data.dto.model.InstituteDto;
import diploma.electronicrecordcard.data.entity.Institute;
import diploma.electronicrecordcard.repository.VersionRepository;
import diploma.electronicrecordcard.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class InstituteVersionService extends VersionServiceImpl<InstituteDto, Institute> {

    public InstituteVersionService(VersionRepository<Institute> repository, Mapper<InstituteDto, Institute> mapper) {
        super(repository, mapper);
    }

}
