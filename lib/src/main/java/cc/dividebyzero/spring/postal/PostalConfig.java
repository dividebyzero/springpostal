package cc.dividebyzero.spring.postal;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class PostalConfig {


    @Value("${cc.dividebyzero.spring.postal.auth.strategy:NONE}")
    private String authStrategy;

    @Value("${cc.dividebyzero.spring.postal.auth.user:''}")
    private String authUser;

    @Value("${cc.dividebyzero.spring.postal.auth.password:''}")
    private String authPass;

    @Value("${cc.dividebyzero.spring.postal.baseurl:http://localhost:8080}")
    private String baseUrl;

    @Value("${cc.dividebyzero.spring.postal.proxy.host:''}")
    private String proxyHost;

    @Value("${cc.dividebyzero.spring.postal.proxy.port:0}")
    private int proxyPort;

    @Value("${cc.dividebyzero.spring.postal.proxy.enabled:false}")
    private boolean proxyEnabled;



}
