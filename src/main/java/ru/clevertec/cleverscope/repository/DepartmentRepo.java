package ru.clevertec.cleverscope.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.clevertec.cleverscope.entity.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Long>, JpaSpecificationExecutor<Department> {
}
