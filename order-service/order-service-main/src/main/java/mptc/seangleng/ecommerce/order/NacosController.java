package mptc.seangleng.ecommerce.order;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/nacos-config")
@RequiredArgsConstructor
public class NacosController {
    private final NacosConfigProps nacosConfigProps;

    @GetMapping
    public Map<String, Object> getConfig() {
        return Map.of(
                "serviceName", nacosConfigProps.getName(),
                "serviceInfo", nacosConfigProps.getInfo(),
                "serviceVersion", nacosConfigProps.getVersion()
        );
    }

    @GetMapping("/secured")
    public Map<String, Object> getSecureConfig(@AuthenticationPrincipal Jwt jwt) {
        return Map.of(
                "serviceName", nacosConfigProps.getName(),
                "serviceInfo", nacosConfigProps.getInfo(),
                "serviceVersion", nacosConfigProps.getVersion(),
                "accessToken", jwt.getTokenValue()
        );
    }
}
