package diploma.electronicrecordcard.service.mapper.impl;

import diploma.electronicrecordcard.data.dto.export.ExportDataDto;
import diploma.electronicrecordcard.data.dto.response.ExportDataResponseDto;
import diploma.electronicrecordcard.service.mapper.Mapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExportDataMapper implements Mapper<ExportDataResponseDto, ExportDataDto> {

    @Value("${export.date-format}")
    String dateFormat;

    @Override
    public ExportDataResponseDto toDto(ExportDataDto exportDataDto) {
        return ExportDataResponseDto.builder()
                .id(exportDataDto.studentRecordBookNumber())
                .student(String.format(
                        "%s %s %s",
                        exportDataDto.studentLastName(),
                        exportDataDto.studentFirstName(),
                        exportDataDto.studentMiddleName()
                ))
                .group(exportDataDto.groupName())
                .discipline(exportDataDto.subjectName())
                .type(exportDataDto.controlTypeName())
                .value(exportDataDto.markName())
                .date(exportDataDto.completionDate().format(DateTimeFormatter.ofPattern(dateFormat)))
                .note(exportDataDto.note())
                .build();
    }

    @Override
    public ExportDataDto toEntity(ExportDataResponseDto exportDataResponseDto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
