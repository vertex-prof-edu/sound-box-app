package vertex.pro.edu.soung_box_app.security.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vertex.pro.edu.soung_box_app.converter.ConfirmationTokenConverter;
import vertex.pro.edu.soung_box_app.entity.token.ConfirmationTokenEntity;
import vertex.pro.edu.soung_box_app.entity.token.model.ConfirmationToken;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.exception.TokenExpiredException;
import vertex.pro.edu.soung_box_app.repository.ConfirmationTokenRepository;
import vertex.pro.edu.soung_box_app.service.crud.CustomUserDetailsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final CustomUserDetailsService userDetailsService;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final ConfirmationTokenConverter confirmationTokenConverter;

    public void saveConfirmationToken(ConfirmationTokenEntity token) {
        confirmationTokenRepository.save(token);
    }

    public ConfirmationTokenEntity getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public void setConfirmedAt(String token) {
        confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

    public ConfirmationToken resendConfirmationToken() {
        UserEntity user = userDetailsService.getCurrentNotConfirmedUser();

        List<ConfirmationTokenEntity> oldTokens = confirmationTokenRepository.findTokenByUserId(user.getId());

        LocalDateTime time = LocalDateTime.now();

        for (ConfirmationTokenEntity token: oldTokens) {
            if (!time.isAfter(token.getExpiresAt())) {
                throw new TokenExpiredException(YOU_CANT_RESEND_TOKEN_DONT_EXPIRED);
            }
        }
        Boolean checkUserVerification = user.getEnabled();

        if (checkUserVerification != null) {
            throw new TokenExpiredException(YOU_CANT_RESEND_TOKEN_ALREADY_CONFIRMED_TOKEN);
        } else {
            String newToken = UUID.randomUUID().toString();

            ConfirmationTokenEntity newConfirmationTokenEntity = new ConfirmationTokenEntity(
                    newToken,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(15),
                    user);

            confirmationTokenRepository.save(newConfirmationTokenEntity);

            return confirmationTokenConverter.fromEntity(newConfirmationTokenEntity);
        }
    }

    private final static String YOU_CANT_RESEND_TOKEN_DONT_EXPIRED = "you cant resend token, token didn't expire yet";
    private final static String YOU_CANT_RESEND_TOKEN_ALREADY_CONFIRMED_TOKEN =
            "you cant resend token, token already confirmed";
}
