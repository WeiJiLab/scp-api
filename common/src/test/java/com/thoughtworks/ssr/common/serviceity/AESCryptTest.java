package com.thoughtworks.ssr.common.serviceity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AESCryptTest {

    @Test
    public void should_encrypt_str_given_str() {
        var str = "hello";

        var encrypt = AESCrypt.encrypt(str);
        assertEquals(str, AESCrypt.decrypt(encrypt));
    }

    @Test
    public void should_decrypt_str_given_decryptStr() {

        Assertions.assertAll(
                () -> assertEquals("hello", AESCrypt.decrypt("6vCerOM8BK+jWTo4KJIpYA==")),
                () -> assertEquals("hello", AESCrypt.decrypt("s622tqd5WAyAQuukeTvwFQ==")),
                () -> assertEquals("hello", AESCrypt.decrypt("CCGpDD3JxTUz+TWhfxOffQ==")),
                () -> assertEquals("hello", AESCrypt.decrypt("eH+DqCxCI20E9d0oLsno+Q==")),
                () -> assertEquals("hello", AESCrypt.decrypt("HqxJyl5IEF2CR1iT/Ylkng=="))
        );
    }

}