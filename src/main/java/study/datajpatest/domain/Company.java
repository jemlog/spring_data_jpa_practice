package study.datajpatest.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {

    @Id @GeneratedValue
    @Column(name = "company_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Kind kind;

    @OneToMany(mappedBy = "company")
    private List<Member> members = new ArrayList<>();

    public Company(String name, Kind kind) {
        this.name = name;
        this.kind = kind;
    }

    // 연관관계 편의 메서드
    public void setMember(Member member)
    {
        this.members.add(member);
        member.setCompany(this);
    }
}
