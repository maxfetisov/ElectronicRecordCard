package diploma.electronicrecordcard.service.criteria.impl;

import diploma.electronicrecordcard.data.entity.Subject;
import diploma.electronicrecordcard.service.account.AuthorityService;
import diploma.electronicrecordcard.service.criteria.rolespecification.RoleSpecificationService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

@Service
public class SubjectCriteriaService extends CriteriaServiceImpl<Subject, Long> {


    public SubjectCriteriaService(AuthorityService authorityService,
                                  JpaRepository<Subject, Long> repository,
                                  JpaSpecificationExecutor<Subject> specificationExecutor,
                                  RoleSpecificationService<Subject> roleSpecificationService) {
        super(authorityService, repository, specificationExecutor, roleSpecificationService);
    }
}
