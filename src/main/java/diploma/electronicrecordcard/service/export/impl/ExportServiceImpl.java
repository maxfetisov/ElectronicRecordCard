package diploma.electronicrecordcard.service.export.impl;

import diploma.electronicrecordcard.data.dto.response.ExportDataResponseDto;
import diploma.electronicrecordcard.data.enumeration.RoleName;
import diploma.electronicrecordcard.data.dto.export.ExportDataDto;
import diploma.electronicrecordcard.repository.model.StudentMarkRepository;
import diploma.electronicrecordcard.service.account.AuthorityService;
import diploma.electronicrecordcard.service.export.ExportService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ExportServiceImpl implements ExportService {

    AuthorityService authorityService;

    StudentMarkRepository studentMarkRepository;

    Mapper<ExportDataResponseDto, ExportDataDto> mapper;

    @Override
    public List<ExportDataResponseDto> export(Long subjectId, Integer groupId) {
        authorityService.checkRolesAndThrow(List.of(RoleName.DEAN_OFFICE_EMPLOYEE, RoleName.ADMINISTRATOR));
        return studentMarkRepository.getExportData(subjectId, groupId).stream()
                .map(mapper::toDto)
                .toList();
    }

}
