package diploma.electronicrecordcard.service.account;

import diploma.electronicrecordcard.data.dto.model.UserDto;
import diploma.electronicrecordcard.data.dto.request.AuthenticationRequestDto;
import diploma.electronicrecordcard.data.dto.request.RefreshRequestDto;
import diploma.electronicrecordcard.data.dto.request.UserCreateRequestDto;
import diploma.electronicrecordcard.data.dto.response.AuthenticationResponseDto;

public interface AccountService {

    UserDto register(UserCreateRequestDto registerRequest);

    AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequest);

    AuthenticationResponseDto refresh(RefreshRequestDto refreshRequest);

    void logout();
}
