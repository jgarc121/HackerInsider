package hacker.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="hacker.post")
@Data
public class SecurityPostProperties {

    private int pageSize;
}
