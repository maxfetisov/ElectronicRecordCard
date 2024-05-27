package diploma.electronicrecordcard.service.account.impl;

import diploma.electronicrecordcard.data.dto.model.UserDto;
import diploma.electronicrecordcard.data.dto.request.AuthenticationRequestDto;
import diploma.electronicrecordcard.data.dto.request.ChangePasswordRequestDto;
import diploma.electronicrecordcard.data.dto.request.RefreshRequestDto;
import diploma.electronicrecordcard.data.dto.request.UserCreateRequestDto;
import diploma.electronicrecordcard.data.dto.response.AuthenticationResponseDto;
import diploma.electronicrecordcard.data.entity.RefreshToken;
import diploma.electronicrecordcard.data.entity.User;
import diploma.electronicrecordcard.repository.model.RefreshTokenRepository;
import diploma.electronicrecordcard.repository.model.UserRepository;
import diploma.electronicrecordcard.service.account.AccountService;
import diploma.electronicrecordcard.service.account.AuthorityService;
import diploma.electronicrecordcard.service.account.JwtService;
import diploma.electronicrecordcard.service.mapper.impl.UserMapper;
import diploma.electronicrecordcard.service.model.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    PasswordEncoder passwordEncoder;

    UserService userService;

    UserRepository userRepository;

    AuthenticationManager authenticationManager;

    JwtService jwtService;

    RefreshTokenRepository refreshTokenRepository;

    AuthorityService authorityService;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDto register(UserCreateRequestDto registerRequest) {
        return userService.create(UserDto.builder()
                .login(registerRequest.login())
                .password(passwordEncoder.encode(registerRequest.password()))
                .lastName(registerRequest.lastName())
                .firstName(registerRequest.firstName())
                .middleName(registerRequest.middleName())
                .email(registerRequest.email())
                .phoneNumber(registerRequest.phoneNumber())
                .recordBookNumber(registerRequest.recordBookNumber())
                .deleted(false)
                .instituteId(registerRequest.instituteId())
                .groupId(registerRequest.groupId())
                .roles(registerRequest.roles())
                .build());
    }

    @Override
    @Transactional
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.login(), authenticationRequest.password())
        );
        var user = userRepository.findByLoginWithRoles(authenticationRequest.login())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return authenticate(user);
    }

    @Override
    @Transactional
    public AuthenticationResponseDto refresh(RefreshRequestDto refreshRequest) {
        var userId = Long.parseLong(jwtService.extractRefreshUserId(refreshRequest.refreshToken()));
        if(!refreshTokenRepository.existsByTokenAndUserId(refreshRequest.refreshToken(), userId)) {
            throw new UsernameNotFoundException("Refresh token not found");
        }
        var user = userRepository.findByIdWithRoles(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return authenticate(user);
    }

    @Override
    @Transactional
    public void changePassword(ChangePasswordRequestDto changePasswordRequest) {
        var user = userRepository.findById(authorityService.getCurrentUser().id())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if(!passwordEncoder.matches(changePasswordRequest.oldPassword(), user.getPassword())) {
           throw new BadCredentialsException("Old password does not match");
        }
        user.setPassword(passwordEncoder.encode(changePasswordRequest.newPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void logout() {
        Optional.ofNullable(authorityService.getCurrentUser())
                .ifPresent(user -> refreshTokenRepository.deleteByUserId(user.id()));
    }

    private AuthenticationResponseDto authenticate(User user) {
        refreshTokenRepository.deleteByUserId(user.getId());
        return AuthenticationResponseDto.builder()
                .token(jwtService.generateToken(user))
                .refreshToken(refreshTokenRepository.save(RefreshToken.builder()
                        .user(user)
                        .token(jwtService.generateRefreshToken(String.valueOf(user.getId())))
                        .build()).getToken())
                .build();
    }

}
