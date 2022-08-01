package ar.gob.inti.argentinaprograma.miportfolio.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.gob.inti.argentinaprograma.miportfolio.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class TokenUtil {
	
    @Value("${token.expiration}")
	private long EXPIRE_DURATION;
	
 	@Value("${token.secret}")
	private String SECRET_KEY;
	
	public String generateAccessToken(User user) {
		
		return Jwts.builder()
				.setSubject(String.format("%s,%s", user.getId(), user.getUsername()))
				.setIssuer("AppJwt")
				.claim("roles", user.getRoles().toString())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
	}
	
	public boolean validateAccessToken(String token) {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException ex) {
			System.out.println("JWT expired" + ex.getMessage());
		} catch (IllegalArgumentException ex) {
			System.out.println("Token is null, empty or only whitespace " + ex.getMessage());
		} catch (MalformedJwtException ex) {
			System.out.println("JWT is invalid " + ex);
		} catch (UnsupportedJwtException ex) {
			System.out.println("JWT is not supported " + ex);
		} catch (SignatureException ex) {
			System.out.println("Signature validation failed");
		}
		
		return false;
	}
	
	public String getSubject(String token) {
		return parseClaims(token).getSubject();
	}
	
	public Claims parseClaims(String token) {
		return Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody();
	}
}