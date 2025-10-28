package com.shoppinglist.shoppinglist2;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.shoppinglist.shoppinglist2.domain.User;
import com.shoppinglist.shoppinglist2.domain.UserRepository;
import com.shoppinglist.shoppinglist2.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoogleAuthService {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    public String authenticateAndGenerateJwt(String idToken) throws Exception {

        // 1. Google ID 토큰 검증기 설정
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(),
                new GsonFactory())
                // 2. Client ID를 설정하여 토큰의 'aud' 필드를 검증합니다.
                .setAudience(Collections.singletonList(googleClientId))
                .build();

        // 3. ID 토큰 파싱 및 검증
        GoogleIdToken googleIdToken = verifier.verify(idToken);

        if (googleIdToken == null) {
            System.err.println("Google ID 토큰 검증 실패. 토큰: " + idToken.substring(0, 50) + "...");
            throw new IllegalArgumentException("유효하지 않거나 만료된 Google ID 토큰입니다.");
        }

        // 4. Payload에서 사용자 정보 추출
        GoogleIdToken.Payload payload = googleIdToken.getPayload();
        String userEmail = payload.getEmail();

        String userName = (String) payload.get("name"); // 사용자 이름도 추출

        System.out.println("✅ Google 인증 성공. 추출된 이메일: " + userEmail);

        // 4. DB에 사용자 등록/조회 로직 추가 (UsernameNotFoundException 방지)
        Optional<User> existingUser = userRepository.findByUsername(userEmail);

        if (existingUser.isEmpty()) {
            // 새 사용자라면 DB에 저장 (Google 로그인의 경우, 비밀번호는 사용하지 않음)
            User newUser = new User(userEmail, "", "USER");
            userRepository.save(newUser);
            System.out.println("✨ 새 Google 사용자(" + userEmail + ")를 DB에 등록했습니다.");
        }

        // 5. JWT 생성 및 반환
        return jwtService.generateToken(userEmail);
    }
}
