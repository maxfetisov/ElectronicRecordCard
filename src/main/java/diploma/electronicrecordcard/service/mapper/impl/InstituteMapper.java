package diploma.electronicrecordcard.service.mapper.impl;

import diploma.electronicrecordcard.data.dto.InstituteDto;
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
                .build();
    }

    @Override
    public Institute toEntity(InstituteDto instituteDto) {
        return Institute.builder()
                .id(instituteDto.getId())
                .name(instituteDto.getName())
                .fullName(instituteDto.getFullName())
                .build();
    }

}
