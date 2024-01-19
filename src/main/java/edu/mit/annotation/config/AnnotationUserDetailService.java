package edu.mit.annotation.config;

import edu.mit.annotation.realdto.MemberDTO;
import edu.mit.annotation.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AnnotationUserDetailService implements UserDetailsService {

    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDTO userData = memberService.getMemberData(username);
        UserDetails user = null;
        if(userData != null){
            user = User.builder()
                    .username(userData.getEmail())
                    .password(userData.getPassword())
                    .build();
        }else{
            throw new UsernameNotFoundException("존재하지 않는 회원입니다.");
        }
        return user;
    }

}
