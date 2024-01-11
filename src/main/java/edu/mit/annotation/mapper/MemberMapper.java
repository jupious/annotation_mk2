package edu.mit.annotation.mapper;

import edu.mit.annotation.realdto.MemberDTO;

public interface MemberMapper {
    MemberDTO loginCheck(String email);
}
