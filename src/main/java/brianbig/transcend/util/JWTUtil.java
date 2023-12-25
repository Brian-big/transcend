package brianbig.transcend.util;


import brianbig.transcend.config.JWTConfigProperties;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * A utility class for handling jwt tokens
 *
 * @author Brian Barasa
 */

@Component
public class JWTUtil {

    Logger log = LoggerFactory.getLogger(getClass());
    private final String jwtSecret;

    private final int jwtExpirationMs;
    private final int refreshExpirationMs;

    @Autowired
    public JWTUtil(JWTConfigProperties configProperties) {

        int defaultJwtExpirationMs = 86400000; // 1 day
        int defaultJwtRefreshExpirationMs = 864000000; // 10 days

        jwtSecret = configProperties.jwtSecret();
        try {
            defaultJwtExpirationMs = Integer.parseInt(configProperties.jwtExpirationMs());
        } catch (NumberFormatException e) {
            log.warn("invalid jwt expiration: {}", e.getMessage());
        }
        try {
            defaultJwtRefreshExpirationMs = Integer.parseInt(configProperties.jwtRefreshExpirationMs());
        } catch (NumberFormatException e) {
            log.warn("invalid jwt refresh expiration: {}", e.getMessage());
        }

        refreshExpirationMs = defaultJwtRefreshExpirationMs;
        jwtExpirationMs = defaultJwtExpirationMs;

    }


    /**
     * creates a jwt for the given subject
     *
     * @param username the subject to create an access token for
     * @return refresh jwt token which expires after the configured expiration time
     */
    public String generateJWT(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }


    /**
     * creates a jwt for the given subject with a refresh expiration time
     *
     * @param username the subject to create a refresh token for
     * @return refresh jwt token which expires after the configured refresh expiration time
     */
    public String generateRefreshJwtToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    /**
     * creates a jwt for the given subject with a refresh expiration time
     *
     * @param username subject to create a refresh token for
     * @return refresh jwt token which expires after the configured expiration time
     */
    public String newRefreshToken(String username) {


        return Jwts.builder()
                .setSubject((username))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + refreshExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * creates a new jwt for the given subject
     *
     * @param username subject to create an access token for
     * @return Access token which expires after the configured expiration time
     */
    public String newAccessToken(String username) {

        return Jwts.builder()
                .setSubject((username))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) //expires after 1 day
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }


    /**
     * @param token jwt string
     * @return username from the token
     */
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature:--> {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token:--> {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired:--> {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported:--> {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty:--> {}", e.getMessage());
        }

        return false;
    }


}
