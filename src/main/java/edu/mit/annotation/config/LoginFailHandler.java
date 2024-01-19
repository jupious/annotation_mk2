package edu.mit.annotation.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.mit.annotation.realdto.ResponseDTO;
import edu.mit.annotation.realdto.ResponseDataCode;
import edu.mit.annotation.realdto.ResponseDataStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class LoginFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String errorMessage;
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode(ResponseDataCode.ERROR);
        responseDTO.setStatus(ResponseDataStatus.ERROR);

        if(exception instanceof BadCredentialsException){
            errorMessage = "이메일 또는 비밀번호가 틀렸습니다. 확인해주세요.";
        }else if(exception instanceof InternalAuthenticationServiceException){
            errorMessage = "내부 시스템 문제로 로그인 할 수 없습니다. 관리자에게 문의해주세요.";
        }else if(exception instanceof UsernameNotFoundException){
            errorMessage = "이메일 또는 비밀번호가 틀렸습니다. 확인해주세요.";
        }else if(exception instanceof AuthenticationCredentialsNotFoundException) {
            errorMessage = "인증요청이 거부되었습니다. 관리자에게 문의해주세요.";
        }else {
            errorMessage = "알 수 없는 오류로 로그인 요청을 처리할 수 없습니다. 관리자에게 문의해주세요.";
        }

        responseDTO.setMessage(errorMessage);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(objectMapper.writeValueAsString(responseDTO));
        response.getWriter().flush();

    }

}
