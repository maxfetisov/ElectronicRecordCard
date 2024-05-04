package diploma.electronicrecordcard.service.account.impl;

import diploma.electronicrecordcard.data.dto.model.UserDto;
import diploma.electronicrecordcard.data.dto.request.AuthenticationRequestDto;
import diploma.electronicrecordcard.data.dto.request.UserCreateRequestDto;
import diploma.electronicrecordcard.data.dto.response.AuthenticationResponseDto;
import diploma.electronicrecordcard.data.entity.User;
import diploma.electronicrecordcard.service.account.AccountService;
import diploma.electronicrecordcard.service.account.JwtService;
import diploma.electronicrecordcard.service.model.UserService;
import diploma.electronicrecordcard.service.mapper.Mapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    PasswordEncoder passwordEncoder;

    UserService userService;

    AuthenticationManager authenticationManager;

    JwtService jwtService;

    Mapper<UserDto, User> userMapper;

    @Override
    public UserDto registerUser(UserCreateRequestDto registerRequest) {
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
    public AuthenticationResponseDto authenticateUser(AuthenticationRequestDto authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.login(), authenticationRequest.password())
        );
        UserDto user = userService.getByLogin(authenticationRequest.login());
        return AuthenticationResponseDto.builder()
                .token(jwtService.generateToken(userMapper.toEntity(user)))
                .build();
    }

}
