package ru.relex.charity.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.relex.charity.security.model.LoginSuccessModel;
import ru.relex.common.models.CurrentUser;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CharityLoginSuccessHandler implements AuthenticationSuccessHandler {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Authentication authentication) throws IOException {
        if (!(authentication.getPrincipal() instanceof CurrentUser)) {
            throw new AuthenticationServiceException("User instance not of CurrentUser!");
        }

        CurrentUser principal = (CurrentUser) authentication.getPrincipal();

        LoginSuccessModel loginSuccessModel = new LoginSuccessModel(principal);

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(OBJECT_MAPPER.writeValueAsString(loginSuccessModel));
    }
}
