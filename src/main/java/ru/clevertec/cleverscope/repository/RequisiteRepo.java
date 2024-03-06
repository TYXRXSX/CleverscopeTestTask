package ru.clevertec.cleverscope.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.clevertec.cleverscope.entity.Requisite;

@Repository
public interface RequisiteRepo extends JpaRepository<Requisite,Long>, JpaSpecificationExecutor<Requisite> {
}
