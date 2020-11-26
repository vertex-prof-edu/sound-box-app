package vertex.pro.edu.soung_box_app.service.user.security_config.token;

import com.google.common.collect.ImmutableMap;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.compression.GzipCompressionCodec;
import lombok.experimental.FieldDefaults;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
//import vertex.pro.edu.soung_box_app.service.authentication.date_service.DateService;


import java.util.Date;
import java.util.Map;
import java.util.function.Supplier;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static io.jsonwebtoken.impl.TextCodec.BASE64;
import static java.util.Objects.requireNonNull;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.StringUtils.substringBeforeLast;

//@Service
////@NoArgsConstructor
////@AllArgsConstructor
//@FieldDefaults(level = PRIVATE, makeFinal = true)
//public class JWTTokenService implements TokenService, Clock {
//    private static final String DOT = ".";
//    private static final GzipCompressionCodec COMPRESSION_CODEC = new GzipCompressionCodec();
//
//    DateService dates;
//    String issuer;
//    int expirationSec;
//    int clockSkewSec;
//    String secretKey;
//
//    JWTTokenService(final DateService dates,
//                    @Value("${jwt.issuer:octoperf}") final String issuer,
//                    @Value("${jwt.expiration-sec:86400}") final int expirationSec,
//                    @Value("${jwt.clock-skew-sec:300}") final int clockSkewSec,
//                    @Value("${jwt.secret:secret}") final String secret) {
//        super();
//        this.dates = requireNonNull(dates);
//        this.issuer = requireNonNull(issuer);
//        this.expirationSec = expirationSec;
//        this.clockSkewSec = clockSkewSec;
//        this.secretKey = BASE64.encode(requireNonNull(secret));
//    }
//
//    @Override
//    public String permanent(Map<String, String> attributes) {
//        return newToken(attributes, 0);
//    }
//
//    @Override
//    public String expiring(Map<String, String> attributes) {
//        return newToken(attributes, expirationSec);
//    }
//
//    private static Map<String, String> parseClaims(final Supplier<Claims> toClaims) {
//        try {
//            final Claims claims = toClaims.get();
//            final ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
//            for (final Map.Entry<String, Object> e: claims.entrySet()) {
//                builder.put(e.getKey(), String.valueOf(e.getValue()));
//            }
//            return builder.build();
//        } catch (final IllegalArgumentException | JwtException e) {
//            return ImmutableMap.of();
//        }
//    }
//
//    private String newToken(final Map<String, String> attributes, final int expiresInSec) {
//        final DateTime now = dates.now();
//        final Claims claims = Jwts
//                .claims()
//                .setIssuer(issuer)
//                .setIssuedAt(now.toDate());
//
//        if (expiresInSec > 0) {
//            final DateTime expiresAt = now.plusSeconds(expiresInSec);
//            claims.setExpiration(expiresAt.toDate());
//        }
//        claims.putAll(attributes);
//
//        return Jwts
//                .builder()
//                .setClaims(claims)
//                .signWith(HS256, secretKey)
//                .compressWith(COMPRESSION_CODEC)
//                .compact();
//    }
//
//    @Override
//    public Map<String, String> untrusted(final String token) {
//        final JwtParser parser = Jwts
//                .parser()
//                .requireIssuer(issuer)
//                .setClock(this)
//                .setAllowedClockSkewSeconds(clockSkewSec);
//
//        final String withoutSignature = substringBeforeLast(token, DOT) + DOT;
//        return parseClaims(() -> parser.parseClaimsJwt(withoutSignature).getBody());
//    }
//
//    @Override
//    public Map<String, String> verify(final String token) {
//        final JwtParser parser = Jwts
//                .parser()
//                .requireIssuer(issuer)
//                .setClock(this)
//                .setAllowedClockSkewSeconds(clockSkewSec)
//                .setSigningKey(secretKey);
//        return parseClaims(() -> parser.parseClaimsJws(token).getBody());
//    }
//
//    @Override
//    public Date now() {
//        final DateTime now = dates.now();
//        return now.toDate();
//    }
//}
