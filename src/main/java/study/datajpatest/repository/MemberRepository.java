package study.datajpatest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpatest.domain.Company;
import study.datajpatest.domain.Member;
import study.datajpatest.dto.MemberDto;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {




     List<Member> findTop3By();

     @Query("select new study.datajpatest.dto.MemberDto(m.name,m.address,m.company.name) from Member m")
     List<MemberDto> findByDto();

     @EntityGraph(attributePaths = {"company"})
     List<Member> findAll();

     @EntityGraph(attributePaths = {"company"})
     @Query("select m from Member m where m.name = :name")
     Member findByName(@Param("name") String name);

     @EntityGraph(attributePaths = {"company"})
     @Query("select m from Member m")
     Page<Member> findPage(Pageable pageable);
}
