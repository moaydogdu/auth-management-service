package com.oguz.authmanagementservice.auth.util;

import lombok.experimental.UtilityClass;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import java.io.IOException;
import java.io.StringReader;
import java.security.PrivateKey;
import java.security.PublicKey;

@UtilityClass
public class KeyConverter {

    public PrivateKey convertPrivateKey(
            final String privateKeyPem
    ) {
        final StringReader keyReader = new StringReader(privateKeyPem);

        try {
            PrivateKeyInfo privateKeyInfo = PrivateKeyInfo
                    .getInstance(new PEMParser(keyReader).readObject());
            return new JcaPEMKeyConverter().getPrivateKey(privateKeyInfo);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public PublicKey convertPublicKey(
            final String publicKeyPem
    ) {
        final StringReader keyReader = new StringReader(publicKeyPem);

        try {
            SubjectPublicKeyInfo publicKeyInfo = SubjectPublicKeyInfo
                    .getInstance(new PEMParser(keyReader).readObject());
            return new JcaPEMKeyConverter().getPublicKey(publicKeyInfo);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
