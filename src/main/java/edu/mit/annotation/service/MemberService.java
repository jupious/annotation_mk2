package edu.mit.annotation.service;

import edu.mit.annotation.realdto.MemberDTO;

public interface MemberService {
    //입력받은 이메일을 통해 회원정보를 가져오는 메서드
    MemberDTO getMemberData(String email);
}
