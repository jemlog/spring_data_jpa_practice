package study.datajpatest.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpatest.domain.*;
import study.datajpatest.dto.MemberDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    CompanyRepository companyRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("쿼리 메서드, 메서드 이름으로 테스트")
    void methodTest()
    {
//        Member member1 = new Member("member1", 24, Grade.VIP, new Address("서울", "송파구"));
//
//        Member member2 = new Member("member2", 24, Grade.BASIC, new Address("위례", "수정구"));
//
//        Member member3 = new Member("member3", 24, Grade.BASIC, new Address("경남", "성산구"));
//
//        memberRepository.save(member1); // persist 포함!
//        memberRepository.save(member2);
//        memberRepository.save(member3);
//        Member findMember = memberRepository.findByName("member1");
//        List<Member> top3By = memberRepository.findTop3By();
//        for (Member member : top3By) {
//            System.out.println("member = " + member.getName());
//        }
//        Assertions.assertThat("member1").isEqualTo(findMember.getName());
    }

    @Test
    @DisplayName("company와 member 엮어서 테스트")
    void 종합테스트()
    {
        //given

        Member member1 = new Member("member1", 24, Grade.VIP, new Address("서울", "송파구"));
        Member member2 = new Member("member2", 25, Grade.BASIC, new Address("위례", "수정구"));
        Member member3 = new Member("member3", 26, Grade.BASIC, new Address("경남", "성산구"));
        Member member4 = new Member("member4", 27, Grade.VIP, new Address("경기", "성남시"));
        Member member5 = new Member("member5", 30, Grade.BASIC, new Address("제주", "애월읍"));
        Member member6 = new Member("member6", 31, Grade.BASIC, new Address("경북", "대구"));
        Member member7 = new Member("member7", 40, Grade.VIP, new Address("전라", "해남"));
        Member member8 = new Member("member8", 41, Grade.BASIC, new Address("전북", "김제"));
        Member member9 = new Member("member9", 42, Grade.BASIC, new Address("충북", "대전"));
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);
        memberRepository.save(member5);
        memberRepository.save(member6);
        memberRepository.save(member7);
        memberRepository.save(member8);
        memberRepository.save(member9);
        Company samsung = new Company("삼성", Kind.IT);
        samsung.setMember(member1);
        samsung.setMember(member2);
        samsung.setMember(member3);
        samsung.setMember(member4);

        companyRepository.save(samsung);
        Company jaeil = new Company("제일제약", Kind.MEDICINE);
        jaeil.setMember(member5);
        jaeil.setMember(member6);
        jaeil.setMember(member7);
        jaeil.setMember(member8);
        jaeil.setMember(member9);

        companyRepository.save(jaeil);

        em.flush();
        em.clear();

        // when
        List<Company> result = companyRepository.findAll();
        System.out.println("쿼리 다시 나가는지 테스트 해볼께요");
        for (Company company : result) {
            System.out.println(company.getMembers().get(0).getName());
        }


    }

    @Test
    @DisplayName("DTO 테스트")
    void DTO반환테스트(){
        Member member1 = new Member("member1", 24, Grade.VIP, new Address("서울", "송파구"));
        Member member2 = new Member("member2", 24, Grade.BASIC, new Address("위례", "수정구"));
        Member member3 = new Member("member3", 24, Grade.BASIC, new Address("경남", "성산구"));
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        List<MemberDto> byDto = memberRepository.findByDto();
        for (MemberDto memberDto : byDto) {
            System.out.println("memberDto = " + memberDto);
        }
    }

}