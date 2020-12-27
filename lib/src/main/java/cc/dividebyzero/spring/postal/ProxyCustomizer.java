package cc.dividebyzero.spring.postal;

import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.protocol.HttpContext;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class ProxyCustomizer implements RestTemplateCustomizer {

    final String proxyHost;
    final int proxyPort;

    public ProxyCustomizer(String proxyHost, int proxyPort) {
        this.proxyHost = proxyHost;
        this.proxyPort = proxyPort;
    }


    @Override
    public void customize(org.springframework.web.client.RestTemplate restTemplate) {
        HttpHost proxyHttpHost = new  HttpHost(proxyHost,proxyPort);
        HttpClient proxyHttpClient = HttpClientBuilder.create()
                .setRoutePlanner(new DefaultProxyRoutePlanner(proxyHttpHost){
                    @Override
                    protected HttpHost determineProxy(HttpHost target, HttpRequest request, HttpContext context) throws HttpException {
                        return super.determineProxy(target, request, context);
                    }
                })
                .build();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(proxyHttpClient));
    }
}
