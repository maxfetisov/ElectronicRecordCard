package diploma.electronicrecordcard.service.account;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {


    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    String extractLogin(String token);

    <T> T extractRefreshClaim(String token, Function<Claims, T> claimsResolver);

    String extractRefreshUserId(String token);

    String generateToken(UserDetails userDetails);

    String generateToken(UserDetails userDetails, Map<String, Object> extraClaims);

    String generateRefreshToken(String userId);

    boolean isTokenValid(String token, UserDetails userDetails);

    boolean isTokenExpired(String token);

}
