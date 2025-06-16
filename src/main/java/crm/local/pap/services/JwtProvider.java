package crm.local.pap.services;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;



@Service
public class JwtProvider {
    
    private PrivateKey privateKey;
    private PublicKey publicKey;


    public JwtProvider() {
   
        KeyPair keys = Jwts.SIG.RS256.keyPair().build();

        this.privateKey = keys.getPrivate();
        this.publicKey = keys.getPublic();

    }

}
