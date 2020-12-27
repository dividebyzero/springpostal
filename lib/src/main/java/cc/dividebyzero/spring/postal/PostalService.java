package cc.dividebyzero.spring.postal;

import cc.dividebyzero.spring.postal.parser.LabelToPostalAddressConverter;
import cc.dividebyzero.spring.postal.parser.ParserLabel;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostalService {

    Logger log = LoggerFactory.getLogger(PostalService.class);

    private PostalConfig postalConfig;

    private RestTemplate restTemplate=null;

    public PostalService(RestTemplateBuilder restTemplateBuilder, PostalConfig postalConfig) {
        this.postalConfig = postalConfig;
        log.info("---------------------POSTALSERVICE--------------\nbaseUrl>>"+postalConfig.getBaseUrl()
                +"<<\nuseProxy>>"+postalConfig.isProxyEnabled()+"<<"
                +"<<\nProxyHost>>"+postalConfig.getProxyHost()+"<<"
                +"<<\nProxyPort>>"+postalConfig.getProxyPort()+"<<"
                +"\n-----------------------------------------------------");
        if(postalConfig.isProxyEnabled()){
            restTemplateBuilder = new RestTemplateBuilder(
                    new ProxyCustomizer(postalConfig.getProxyHost(), postalConfig.getProxyPort())
            );
        }

        //todo: auth,

        this.restTemplate = restTemplateBuilder.build();

    }


    public PostalAddress getStructuredAddress(final String addressLineInput){
        JSONObject body = new JSONObject();
        body.put("query",addressLineInput);

        HttpEntity<String> httpEntity = new HttpEntity<>(body.toString());
        ResponseEntity<ParserLabel[]> responseEntity = restTemplate.postForEntity(
                postalConfig.getBaseUrl()+"/parser",
                httpEntity,
                ParserLabel[].class
        );

        ParserLabel[] labels = responseEntity.getBody();

        return LabelToPostalAddressConverter.convert(labels);
    }


}
