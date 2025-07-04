package crm.local.pap.services;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtProvider {

    private final PrivateKey privateKey;
    private final PublicKey publicKey;
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000;

    public JwtProvider() {
        KeyPair keyPair = Jwts.SIG.RS256.keyPair().build();
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
    }


    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRE_DURATION);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(privateKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }


    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}