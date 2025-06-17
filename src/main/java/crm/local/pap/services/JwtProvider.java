package crm.local.pap.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

@Service
public class JwtProvider {

    // Mudei de RS256 para HS512, puramente por teste pk nn faço a mínima qual foi o
    // repositório que comecei
    // a roubar e em qual é que já tou, vou entrar pá política com esse Frankenstein
    // de roubos daqui e dali..

    // Nota pa mim msm: EXPERIMENTA SÓ DAR HARDCODE NA KEY DENOVO A PENSAR QUE É A
    // MANEIRA MAIS FÁCIL DE A ASSEGURAR QUE VAIS VER O QUE É QUE NAO TE FAÇO
    // CRLHHJH!!!!!!!!!

    // Voltei pa RS256..
    // Deu td errado e tenho de refazer TUDOOOO 😊😊😊😊😊😊😊😊

    private final PrivateKey privateKey;
    private final PublicKey publicKey;
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 horinhas até expirar (já nem sei se é 24 com essas contas)

    public JwtProvider() {
        KeyPair keyPair = Jwts.SIG.RS256.keyPair().build();
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
    }

  
    // Deus Gera o token JWT vinculado com a private key, sabe-se lá como

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRE_DURATION);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(privateKey) // Acho que é isso????
                .compact();
    }

    // Validar o token com a chave puBLICA
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .setSigningKey(publicKey) // Verifica com a chave puBLICA só pa garantii
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    // Estraimos agr o username do token depois de validaaaaaareeeee
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(publicKey) // Verificar só +1 vez com a chave puBLICA, q eu sou é desconfiado
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}