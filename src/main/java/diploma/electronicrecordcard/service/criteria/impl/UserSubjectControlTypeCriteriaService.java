package diploma.electronicrecordcard.service.criteria.impl;

import diploma.electronicrecordcard.data.entity.UserSubjectControlType;
import diploma.electronicrecordcard.service.account.AuthorityService;
import diploma.electronicrecordcard.service.criteria.rolespecification.RoleSpecificationService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

@Service
public class UserSubjectControlTypeCriteriaService extends CriteriaServiceImpl<UserSubjectControlType, Long> {

    public UserSubjectControlTypeCriteriaService(
            AuthorityService authorityService,
            JpaRepository<UserSubjectControlType, Long> repository,
            JpaSpecificationExecutor<UserSubjectControlType> specificationExecutor,
            RoleSpecificationService<UserSubjectControlType> roleSpecificationService
    ) {
        super(authorityService, repository, specificationExecutor, roleSpecificationService);
    }

}
