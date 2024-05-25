package diploma.electronicrecordcard.service.criteria.impl;

import diploma.electronicrecordcard.data.dto.model.UserDto;
import diploma.electronicrecordcard.data.enumeration.RoleName;
import diploma.electronicrecordcard.service.account.AuthorityService;
import diploma.electronicrecordcard.service.criteria.CriteriaService;
import diploma.electronicrecordcard.service.criteria.rolespecification.RoleSpecificationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CriteriaServiceImpl<T, ID> implements CriteriaService<T> {

    AuthorityService authorityService;

    JpaRepository<T, ID> repository;

    JpaSpecificationExecutor<T> specificationExecutor;

    RoleSpecificationService<T> roleSpecificationService;

    @Override
    public List<T> getByCriteria(Specification<T> specification) {
        Optional<Specification<T>> optionalSpecification = Optional.ofNullable(specification);

        Optional<Specification<T>> roleSpecification = getRoleSpecification();

        return Stream.of(optionalSpecification, roleSpecification)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .reduce(Specification::and)
                .map(specificationExecutor::findAll)
                .orElse(repository.findAll());
    }

    @Override
    public Page<T> getByCriteria(Specification<T> specification, Pageable pageable) {
        Optional<Specification<T>> optionalSpecification = Optional.ofNullable(specification);

        Optional<Specification<T>> roleSpecification = getRoleSpecification();

        return Stream.of(optionalSpecification, roleSpecification)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .reduce(Specification::and)
                .map(spec -> specificationExecutor.findAll(spec, pageable))
                .orElse(repository.findAll(pageable));
    }

    private Optional<Specification<T>> getRoleSpecification() {
        List<Specification<T>> roleSpecifications = new ArrayList<>();

        UserDto user = authorityService.getCurrentUser();
        for (RoleName roleName : RoleName.values()) {
            if(authorityService.hasAnyAuthority(List.of(roleName))) {
                roleSpecifications.add(roleSpecificationService.getSpecificationByRole(user, roleName));
            }
        }
        return roleSpecifications.stream()
                .filter(Objects::nonNull)
                .reduce(Specification::or);
    }
}
