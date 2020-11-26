package vertex.pro.edu.soung_box_app.service.user.security_config.token;

import com.google.common.collect.ImmutableMap;
import io.micrometer.core.lang.NonNull;
import vertex.pro.edu.soung_box_app.model.user.User;
import vertex.pro.edu.soung_box_app.repository.UserRepository;

import java.util.Objects;
import java.util.Optional;

//public class TokenAuthenticationService implements UserAuthenticationService {
//    @NonNull
//    TokenService tokens;
//    @NonNull
//    UserRepository userRepository;
//
//    @Override
//    public Optional<String> login(final String username, final String password) {
//        return userRepository
//                .findByUsername(username)
//                .filter(user -> Objects.equals(password, user.getPassword()))
//                .map(user -> tokens.expiring(ImmutableMap.of("username", username)));
//    }
//
//    @Override
//    public Optional<User> findByToken(final String token) {
//        return Optional
//                .of(tokens.verify(token))
//                .map(map -> map.get("username"))
//                .flatMap(userRepository::findByUsername);
//    }
//
//    @Override
//    public void logout(final User user) {
//        // Nothing to doy
//    }
//}
