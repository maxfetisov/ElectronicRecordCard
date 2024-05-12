package diploma.electronicrecordcard.service.criteria.impl;

import diploma.electronicrecordcard.data.entity.Institute;
import diploma.electronicrecordcard.service.account.AuthorityService;
import diploma.electronicrecordcard.service.criteria.rolespecification.RoleSpecificationService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

@Service
public class InstituteCriteriaService extends CriteriaServiceImpl<Institute, Short> {

    public InstituteCriteriaService(AuthorityService authorityService,
                                    JpaRepository<Institute, Short> repository,
                                    JpaSpecificationExecutor<Institute> specificationExecutor,
                                    RoleSpecificationService<Institute> roleSpecificationService) {
        super(authorityService, repository, specificationExecutor, roleSpecificationService);
    }

}
