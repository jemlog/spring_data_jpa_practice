package study.datajpatest.apiController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.datajpatest.domain.Member;
import study.datajpatest.dto.MemberDto;
import study.datajpatest.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/test")
    public Result dtoTest()
    {
        List<MemberDto> byDto = memberRepository.findByDto();
        memberRepository.findAll();
        return new Result(byDto);
    }

    @GetMapping("/test2")
    @Transactional
    public Result entityTest(@RequestParam("name") String name)
    {
        Member result = memberRepository.findByName(name);
        System.out.println(result);
        System.out.println(result.getCompany().getName());
        MemberDto memberDto = new MemberDto(result.getName(), result.getAddress(), result.getCompany().getName());
        System.out.println(memberDto);
        return new Result(memberDto);

    }

    @GetMapping("/testPaging/{number}")
    public Result paging(@PathVariable("number") int number)
    {
        PageRequest pageRequest = PageRequest.of(number,3, Sort.by(Sort.Direction.ASC,"name"));
        Page<Member> result = memberRepository.findPage(pageRequest);
        Page<MemberDto> toMap = result.map(m -> new MemberDto(m.getName(), m.getAddress(), m.getCompany().getName()));
        return new Result(toMap);
    }





    @Data
    @AllArgsConstructor
    static class Result<T>
    {
        private T data;
    }


}
