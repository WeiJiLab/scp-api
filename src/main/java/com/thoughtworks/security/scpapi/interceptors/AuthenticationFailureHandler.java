package com.thoughtworks.security.scpapi.interceptors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthenticationFailureHandler implements AuthenticationEntryPoint, Serializable {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        // TODO: customize response when authentication failed here
        Map<String, Object> results = new HashMap<>() {{
            put("code", HttpServletResponse.SC_UNAUTHORIZED);
            put("message", "Authentication Failed");
            put("error", authException.getMessage());
        }};
        String json = new Gson().toJson(results, new TypeToken<HashMap<String, Object>>(){}.getType());

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        response.setContentType("json/application;chartset=utf-8");
        response.getWriter().write(json);
    }
}
