package vertex.pro.edu.soung_box_app.security.token;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vertex.pro.edu.soung_box_app.entity.token.ConfirmationToken;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;
import vertex.pro.edu.soung_box_app.exception.UserDoesntExistException;
import vertex.pro.edu.soung_box_app.exception.UserNotConfirmedException;
import vertex.pro.edu.soung_box_app.repository.ConfirmationTokenRepository;
import vertex.pro.edu.soung_box_app.service.user.crud.CustomUserDetailsService;
import vertex.pro.edu.soung_box_app.service.user.crud.UserCrudService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final CustomUserDetailsService userDetailsService;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public ConfirmationToken getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public void setConfirmedAt(String token) {
        confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

    public ConfirmationToken resendConfirmationToken() throws Exception {
        UserEntity user = userDetailsService.getCurrentNotConfirmedUser();

        List<ConfirmationToken> oldTokens = confirmationTokenRepository.findTokenByUserId(user.getId());

        LocalDateTime time = LocalDateTime.now();

        for (ConfirmationToken token: oldTokens) {
            if (!time.isAfter(token.getExpiresAt())) {
                throw new Exception("you cant resend token");
            }
        }

        Boolean checkUserVerification = user.getEnabled();

        if (checkUserVerification != null) {
            throw new Exception("you cant resend token");
        } else {
            String newToken = UUID.randomUUID().toString();

            ConfirmationToken newConfirmationToken = new ConfirmationToken(
                    newToken,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(15),
                    user);

            confirmationTokenRepository.save(newConfirmationToken);

            return newConfirmationToken;
        }
    }
}
