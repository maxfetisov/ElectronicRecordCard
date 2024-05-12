package diploma.electronicrecordcard.service.criteria.impl;

import diploma.electronicrecordcard.data.entity.Group;
import diploma.electronicrecordcard.service.account.AuthorityService;
import diploma.electronicrecordcard.service.criteria.rolespecification.RoleSpecificationService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

@Service
public class GroupCriteriaService extends CriteriaServiceImpl<Group, Integer> {

    public GroupCriteriaService(AuthorityService authorityService,
                                JpaRepository<Group, Integer> repository,
                                JpaSpecificationExecutor<Group> specificationExecutor,
                                RoleSpecificationService<Group> roleSpecificationService) {
        super(authorityService, repository, specificationExecutor, roleSpecificationService);
    }

}
