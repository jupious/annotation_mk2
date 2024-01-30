package edu.mit.annotation.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.mit.annotation.realdto.ResponseDTO;
import edu.mit.annotation.realdto.ResponseDataCode;
import edu.mit.annotation.realdto.ResponseDataStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode(ResponseDataCode.SUCCESS);
        responseDTO.setStatus(ResponseDataStatus.SUCCESS);
        System.out.println("여긴 왔나요");
        Map<String,String> items = new HashMap<>();
        items.put("url","/main/portal");
        responseDTO.setItem(items);

        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(objectMapper.writeValueAsString(responseDTO));
        response.getWriter().flush();
    }
}
