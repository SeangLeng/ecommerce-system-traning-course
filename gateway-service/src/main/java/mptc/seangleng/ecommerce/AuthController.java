package mptc.seangleng.ecommerce;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/secured")
    public String secured() {
        return "Login Successfully";
    }

    @GetMapping("/me")
    public Map<String, Object> me(@AuthenticationPrincipal OAuth2User oAuth2User) {
        if  (oAuth2User == null) {
            return Map.of("authenticated", false);
        }

        return Map.of(
                "authenticated", true,
                "permission", oAuth2User.getAuthorities(),
                "userId", oAuth2User.getName()
        );
    }
}
