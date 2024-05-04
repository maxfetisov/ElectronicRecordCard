package diploma.electronicrecordcard.service.version;

import java.util.List;

public interface VersionService<DTO> {

    List<DTO> getByVersion(Long version);

}
