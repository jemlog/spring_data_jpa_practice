package study.datajpatest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import study.datajpatest.domain.Address;

@Data
@AllArgsConstructor
@ToString()
public class MemberDto {

    private String name;
    private Address address;
    private String companyName;
}
