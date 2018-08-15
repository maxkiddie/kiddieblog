/**
 * 
 */
package com.winter.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.winter.exception.TokenEmptyException;

/**
 * @author xuzhaojie
 *
 *         2018年8月3日 上午10:53:20
 */
public class TokenUtil {

	/** token秘钥，请勿泄露，请勿随便修改 backups:JKKLJOoasdlfj */
	public static final String SECRET = "JKKLJOoasdlfj";
	/** token 过期时间: 10天 */
	public static final int calendarInterval = 10;

	private static final String USER_ID = "userId";

	/**
	 * JWT生成Token.<br/>
	 * 
	 * JWT构成: header, payload, signature
	 * 
	 * @param user_id
	 *            登录成功后用户user_id, 参数user_id不可传空
	 */
	public static String createToken(Integer userId) {
		Date iatDate = new Date();
		// expire time
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.DATE, calendarInterval);
		Date expiresDate = nowTime.getTime();

		// header Map
		Map<String, Object> map = new HashMap<>();
		map.put("alg", "HS256");
		map.put("typ", "JWT");

		// build token
		// param backups {iss:Service, aud:APP}
		String token = JWT.create().withHeader(map) // header
				.withClaim("iss", "Kiddie") // payload
				.withClaim("aud", "Client").withClaim(USER_ID, null == userId ? null : userId.toString())
				.withIssuedAt(iatDate) // sign time
				.withExpiresAt(expiresDate) // expire time
				.sign(Algorithm.HMAC256(SECRET)); // signature
		return token;
	}

	/**
	 * 解密Token
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Claim> verifyToken(String token) {
		if (token == null || "".equals(token)) {
			throw new TokenEmptyException("token is empty");
		}
		DecodedJWT jwt = null;
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
		jwt = verifier.verify(token);
		return jwt.getClaims();
	}

	/**
	 * 根据Token获取user_id
	 * 
	 * @param token
	 * @return user_id
	 */
	public static Integer getUserId(String token) {
		Map<String, Claim> claims = verifyToken(token);
		Claim user_id_claim = claims.get(USER_ID);
		return Integer.valueOf(user_id_claim.asString());
	}

	public static void main(String[] args) {
		System.out.println(getUserId("1" + createToken(253545)));
	}
}
