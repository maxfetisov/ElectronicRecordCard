package diploma.electronicrecordcard.service.mapper.impl;

import diploma.electronicrecordcard.data.dto.model.InstituteDto;
import diploma.electronicrecordcard.data.entity.Institute;
import diploma.electronicrecordcard.service.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class InstituteMapper implements Mapper<InstituteDto, Institute> {

    @Override
    public InstituteDto toDto(Institute institute) {
        return InstituteDto.builder()
                .id(institute.getId())
                .name(institute.getName())
                .fullName(institute.getFullName())
                .deleted(institute.getDeleted())
                .version(institute.getVersion())
                .build();
    }

    @Override
    public Institute toEntity(InstituteDto instituteDto) {
        return Institute.builder()
                .id(instituteDto.id())
                .name(instituteDto.name())
                .fullName(instituteDto.fullName())
                .deleted(instituteDto.deleted())
                .version(instituteDto.getVersion())
                .build();
    }

}
