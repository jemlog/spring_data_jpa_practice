package study.datajpatest.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private int age;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    public Member(String name, int age, Grade grade, Address address) {
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.address = address;
    }
}
