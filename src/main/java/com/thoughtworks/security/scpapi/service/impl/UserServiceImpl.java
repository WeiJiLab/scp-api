package com.thoughtworks.security.scpapi.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.thoughtworks.security.scpapi.config.properties.SamlProperties;
import com.thoughtworks.security.scpapi.entity.User;
import com.thoughtworks.security.scpapi.service.UserService;
import com.thoughtworks.security.scpapi.utils.SamlUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

// import java.io.IOException;
// import java.security.cert.CertificateException;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final SamlProperties samlProperties;
    private final RedisTemplate<String, String> redisTemplate;

    /*
     * In our case, we should call this method with User::uid
     */
    @Override
    public Optional<User> findByName(String name) {
        if (name == null) {
            return Optional.empty();
        }

        Object userData = redisTemplate.opsForValue().get(name);
        if (userData == null) {
            return Optional.empty();
        }

        User user = null;
        try {
            user = new Gson().fromJson(userData.toString(), User.class);
        } catch (JsonParseException ex) {
            log.error(ex.getMessage());
            return Optional.empty();
        }

        return Optional.of(user);
    }

    @Override
    public Optional<User> save(String base64Saml) {
        // decode base64 string
        String saml = SamlUtil.base64Decode(base64Saml);

        // validate saml sign with public key
        // try {
        //     SamlUtil.validateSamlSignature(saml, SamlUtil.publicKeyCredential(samlProperties.getCerPath()));
        // } catch (CertificateException | IOException e) {
        //     log.debug(e.getMessage());
        //     return Optional.empty();
        // }

        // validate expiration date
        // TODO: saml validattion logic not completed

        // decrypt saml
        User user = new User(1, "tester", null);

        // create convert saml to json
        String userData = new Gson().toJson(user);

        // put json to redis
        redisTemplate.opsForValue().set(String.valueOf(user.getUid()), userData);

        return Optional.of(user);
    }

    @Override
    public void delete(String username) {
        // TODO: add user data removal logic here
    }
}
