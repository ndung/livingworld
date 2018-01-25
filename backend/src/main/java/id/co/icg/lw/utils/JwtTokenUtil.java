package id.co.icg.lw.utils;

import id.co.icg.lw.domain.user.User;
import id.co.icg.lw.enums.RoleEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private long expiration;

	private String DELIMITER = ",";

	public String createToken(User user) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		String[] temp = new String[user.getRoles().size()];
		for (int i=0; i<user.getRoles().size(); i++)
			temp[i] = String.valueOf(user.getRoles().get(i).getId());
		JwtBuilder builder = Jwts.builder()
				.setId(user.getId())
				.setIssuedAt(now)
				.setSubject(Helper.implode(temp, DELIMITER))
				.setIssuer(user.getCardNumber())
				.signWith(signatureAlgorithm, signingKey);
		if (expiration >= 0) {
			long expMillis = nowMillis + (expiration * 1000);
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}
		return builder.compact();
	}

	public boolean authenticate(String token) {
		try {
			Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secret)).parseClaimsJws(token).getBody();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getCardNumber(String token) {
		try {
			Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secret)).parseClaimsJws(token)
					.getBody();
			return claims.getIssuer();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public String[] getRoles(String token) throws Exception {
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secret)).parseClaimsJws(token)
				.getBody();
		String roleString = claims.getSubject();
		return roleString.split(DELIMITER);
	}

	public String getUserId(String token) {
		try {
			Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secret)).parseClaimsJws(token)
					.getBody();
			return claims.getId();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public boolean isAdmin(String token) throws Exception {
		String[] roles = getRoles(token);
		for (String name : roles) {
			if (name.equals(RoleEnum.ADMIN.name()))
				return true;
		}
		return false;
	}

}