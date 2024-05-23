package diploma.electronicrecordcard.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppEntryPoint implements AuthenticationEntryPoint {

    Map<Class<? extends AuthenticationException>, HttpStatus> statuses = Map.of(
            InsufficientAuthenticationException.class, HttpStatus.UNAUTHORIZED,
            BadCredentialsException.class, HttpStatus.UNAUTHORIZED
    );

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(statuses.getOrDefault(authException.getClass(), HttpStatus.INTERNAL_SERVER_ERROR).value());
    }

}
