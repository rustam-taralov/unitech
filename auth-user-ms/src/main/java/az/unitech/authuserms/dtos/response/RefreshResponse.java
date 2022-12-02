package az.unitech.authuserms.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshResponse {

    private String accessToken;

    public static RefreshResponse of(String accessToken){
        return RefreshResponse.builder()
                .accessToken(accessToken)
                .build();
    }
}
