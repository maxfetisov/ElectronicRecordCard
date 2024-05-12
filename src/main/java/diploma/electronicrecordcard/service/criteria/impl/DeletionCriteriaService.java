package diploma.electronicrecordcard.service.criteria.impl;

import diploma.electronicrecordcard.data.entity.Deletion;
import diploma.electronicrecordcard.service.account.AuthorityService;
import diploma.electronicrecordcard.service.criteria.rolespecification.RoleSpecificationService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

@Service
public class DeletionCriteriaService extends CriteriaServiceImpl<Deletion, Long> {

    public DeletionCriteriaService(AuthorityService authorityService,
                                   JpaRepository<Deletion, Long> repository,
                                   JpaSpecificationExecutor<Deletion> specificationExecutor,
                                   RoleSpecificationService<Deletion> roleSpecificationService) {
        super(authorityService, repository, specificationExecutor, roleSpecificationService);
    }

}
