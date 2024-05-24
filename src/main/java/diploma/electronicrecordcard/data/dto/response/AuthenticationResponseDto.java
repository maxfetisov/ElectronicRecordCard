package diploma.electronicrecordcard.data.dto.response;

import lombok.Builder;

@Builder
public record AuthenticationResponseDto (

        String token,

        String refreshToken

){
}
