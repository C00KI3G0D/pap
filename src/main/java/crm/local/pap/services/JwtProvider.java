package crm.local.pap.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtProvider {

    // Mudei de RS256  para HS512, puramente por teste pk nn faço a mínima qual foi o repositório que comecei
    // a roubar e em qual é que já tou, vou entrar pá política com esse Frankenstein de roubos daqui e dali..


    // Nota pa mim msm: EXPERIMENTA SÓ DAR HARDCODE NA KEY DENOVO A PENSAR QUE É A 
    // MANEIRA MAIS FÁCIL DE A ASSEGURAR QUE VAIS VER O QUE É QUE NAO TE FAÇO CRLHHJH!!!!!!!!!  
    
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 horinhas até expirar ( já nem sei se é demasiado mas que se lix3 )

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRE_DURATION);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token) {

        try {

            Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            return true;

        } catch (Exception ex) {

            // Assim acho que é melhor para casos mais específicos (tipo excessão para JWT's já expirados antes) ( A C H O )
            
            return false;

        }

    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}