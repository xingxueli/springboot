package com.company.project.core.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
@Slf4j
public class JwtUtil {
    /**
     * 签名秘钥
     */
    public static final String SECRET = "8XnwGf6bd24efs73cad832627b4f65L2g5pssssiv5oiR54ix55qAIzaSyD23223";

    public static String createAppToken(String userId,String name) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_id",userId);
        map.put("name",name);
        return createJwtToken(map);
    }

    /**
     * 创建JWT token
     * @return
     */
    public static String createJwtToken(HashMap<String,Object> claims) {
        claims.put("createTime",new Date());
        // 签名算法 ，将对token进行签名
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 通过秘钥签名JWT
        byte[] apiKeySecretBytes = Base64.getDecoder().decode(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setIssuer("user")
                .signWith(signingKey, signatureAlgorithm);
        return builder.compact();
    }

    /**
     * 根据issuer发行者,区分是哪里的token,该方法会抛出异常
     * @param token a {@link String} object.
     * @return a {@link Claims} object.
     */
    public static Claims parseClaims(String token) {

        // This line will throw an exception if it is not a signed JWS (as expected)
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey( Base64.getDecoder().decode(SECRET)).build()
                .parseClaimsJws(token);
        return claimsJws.getBody();
    }


    public static UserContext.UserSession getUser(String token){
        try {
            Claims claims = parseClaims(token);
            return UserContext.UserSession.builder()
                    .userId(claims.get("user_id").toString())
                    .name(claims.get("name").toString())
                    .build();
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        String token = createAppToken("user_123456","王麻子");
        System.out.println(token);
        System.out.println(parseClaims(token));
        System.out.println(getUser(token));
    }
}
