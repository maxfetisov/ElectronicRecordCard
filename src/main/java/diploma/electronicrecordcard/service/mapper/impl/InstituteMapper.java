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
                .version(institute.getVersion())
                .build();
    }

    @Override
    public Institute toEntity(InstituteDto instituteDto) {
        return Institute.builder()
                .id(instituteDto.id())
                .name(instituteDto.name())
                .fullName(instituteDto.fullName())
                .version(instituteDto.getVersion())
                .build();
    }

}
