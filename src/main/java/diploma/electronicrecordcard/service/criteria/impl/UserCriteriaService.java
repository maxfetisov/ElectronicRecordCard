package diploma.electronicrecordcard.service.criteria.impl;

import diploma.electronicrecordcard.data.entity.User;
import diploma.electronicrecordcard.service.account.AuthorityService;
import diploma.electronicrecordcard.service.criteria.rolespecification.RoleSpecificationService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

@Service
public class UserCriteriaService extends CriteriaServiceImpl<User, Long> {


    public UserCriteriaService(AuthorityService authorityService,
                               JpaRepository<User, Long> repository,
                               JpaSpecificationExecutor<User> specificationExecutor,
                               RoleSpecificationService<User> roleSpecificationService) {
        super(authorityService, repository, specificationExecutor, roleSpecificationService);
    }

}
