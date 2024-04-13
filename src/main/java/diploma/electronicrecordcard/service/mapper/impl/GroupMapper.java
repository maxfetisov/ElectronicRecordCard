package diploma.electronicrecordcard.service.mapper.impl;

import diploma.electronicrecordcard.data.dto.GroupDto;
import diploma.electronicrecordcard.data.entity.Group;
import diploma.electronicrecordcard.data.entity.Institute;
import diploma.electronicrecordcard.service.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper implements Mapper<GroupDto, Group> {

    @Override
    public GroupDto toDto(Group group) {
        return GroupDto.builder()
                .id(group.getId())
                .name(group.getName())
                .admissionDate(group.getAdmissionDate())
                .deleted(group.getDeleted())
                .fullName(group.getFullName())
                .instituteId(group.getInstitute().getId())
                .build();
    }

    @Override
    public Group toEntity(GroupDto groupDto) {
        return Group.builder()
                .id(groupDto.getId())
                .name(groupDto.getName())
                .admissionDate(groupDto.getAdmissionDate())
                .deleted(groupDto.getDeleted())
                .fullName(groupDto.getFullName())
                .institute(Institute.builder().id(groupDto.getInstituteId()).build())
                .build();
    }

}
