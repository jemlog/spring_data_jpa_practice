package study.datajpatest.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpatest.domain.Company;

import javax.persistence.Entity;
import java.util.List;

public interface CompanyRepository extends JpaRepository<Company,Long> {

    @Override
    @EntityGraph(attributePaths = {"members"})
    List<Company> findAll();

}
