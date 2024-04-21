package diploma.electronicrecordcard.service;

import diploma.electronicrecordcard.data.dto.model.UserDto;
import diploma.electronicrecordcard.data.dto.request.AuthenticationRequestDto;
import diploma.electronicrecordcard.data.dto.request.UserCreateRequestDto;
import diploma.electronicrecordcard.data.dto.response.AuthenticationResponseDto;

public interface AccountService {

    UserDto registerUser(UserCreateRequestDto registerRequest);

    AuthenticationResponseDto authenticateUser(AuthenticationRequestDto authenticationRequest);

}
