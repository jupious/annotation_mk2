package edu.mit.annotation.service;

import edu.mit.annotation.mapper.MemberMapper;
import edu.mit.annotation.realdto.MemberDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService{

    private MemberMapper memberMapper;
    @Override
    public MemberDTO getMemberData(String email) {
        return memberMapper.loginCheck(email);
    }
}
