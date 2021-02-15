package com.thoughtworks.security.scpapi.interceptors;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        // TODO: customize response when user access denied here
        Map<String, Object> results = new HashMap<>() {{
            put("code", HttpServletResponse.SC_FORBIDDEN);
            put("message", "Access Denied");
        }};
        String json = new Gson().toJson(results, new TypeToken<HashMap<String, Object>>(){}.getType());

        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Unauthorized");
        response.setContentType("json/application;chartset=utf-8");
        response.getWriter().write(json);
    }
}
