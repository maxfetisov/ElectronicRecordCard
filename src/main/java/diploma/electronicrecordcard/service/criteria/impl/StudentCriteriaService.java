package diploma.electronicrecordcard.service.criteria.impl;

import diploma.electronicrecordcard.data.entity.StudentMark;
import diploma.electronicrecordcard.service.account.AuthorityService;
import diploma.electronicrecordcard.service.criteria.rolespecification.RoleSpecificationService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

@Service
public class StudentCriteriaService extends CriteriaServiceImpl<StudentMark, Long> {

    public StudentCriteriaService(AuthorityService authorityService,
                                  JpaRepository<StudentMark, Long> repository,
                                  JpaSpecificationExecutor<StudentMark> specificationExecutor,
                                  RoleSpecificationService<StudentMark> roleSpecificationService) {
        super(authorityService, repository, specificationExecutor, roleSpecificationService);
    }

}
