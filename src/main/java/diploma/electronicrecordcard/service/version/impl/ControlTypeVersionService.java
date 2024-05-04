package diploma.electronicrecordcard.service.version.impl;

import diploma.electronicrecordcard.data.dto.model.ControlTypeDto;
import diploma.electronicrecordcard.data.entity.ControlType;
import diploma.electronicrecordcard.repository.VersionRepository;
import diploma.electronicrecordcard.service.mapper.Mapper;
import org.springframework.stereotype.Service;

@Service
public class ControlTypeVersionService extends VersionServiceImpl<ControlTypeDto, ControlType> {

    public ControlTypeVersionService(VersionRepository<ControlType> repository, Mapper<ControlTypeDto, ControlType> mapper) {
        super(repository, mapper);
    }

}
