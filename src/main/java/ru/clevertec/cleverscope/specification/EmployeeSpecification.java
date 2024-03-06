package ru.clevertec.cleverscope.specification;

import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.clevertec.cleverscope.entity.Department;
import ru.clevertec.cleverscope.entity.Employee;
import ru.clevertec.cleverscope.entity.Requisite;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class EmployeeSpecification implements Specification<Employee> {

    private Long departmentId;
    private String employeeName;
    private String city;
    private String sort;

    @Override
    public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        Join<Employee, Requisite> requisiteJoin = root.join("requisite", JoinType.INNER);
        Join<Employee, Department> departmentJoin = root.join("department", JoinType.INNER);

        if (departmentId != null) {
            predicates.add(criteriaBuilder.equal(departmentJoin.get("id"), departmentId));
        }

        if (employeeName != null) {
            predicates.add(criteriaBuilder.like(root.get("employeeName"), "%" + employeeName + "%"));
        }

        if (city != null) {
            predicates.add(criteriaBuilder.like(root.get("city"), "%" + city + "%"));
        }

        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));


        if (sort != null) {

            String[] params = sort.split(",");

            String field = params[0];
            String direction = params[1];

            if (direction.equals("asc")) {
                if(field.equals("employeeName")){
                    query.orderBy(criteriaBuilder.asc(root.get("name")));
                }
                if(field.equals("bankAccount")){
                    query.orderBy(criteriaBuilder.asc(requisiteJoin.get(field)));
                }
                else {
                    query.orderBy(criteriaBuilder.asc(root.get(field)));
                }
            } else if (direction.equals("desc")) {
                if(field.equals("employeeName")){
                    query.orderBy(criteriaBuilder.desc(root.get("name")));
                }
                if(field.equals("bankAccount")){
                    query.orderBy(criteriaBuilder.desc(requisiteJoin.get(field)));
                }
                else {
                    query.orderBy(criteriaBuilder.desc(root.get(field)));
                }
            }
        }

        return query.getRestriction();
    }
}
