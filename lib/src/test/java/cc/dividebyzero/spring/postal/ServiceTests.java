package cc.dividebyzero.spring.postal;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
@SpringBootApplication
public class ServiceTests {

    Logger logger = LoggerFactory.getLogger(ServiceTests.class);


    @Autowired
    PostalConfig config;

    @Autowired
    PostalService postalService;

    @Test
    public void contextLoads() {
        assertNotNull(config);
        assertNotNull(postalService);
        logger.info("BASE URL >>"+ config.getBaseUrl());

    }

    @Test
    public void testGetAddress(){
        final String input = "Mörfelder Landstraße 362, 60528 Frankfurt am Main";
        final PostalAddress address = postalService.getStructuredAddress(input);
        assertNotNull(address);
        assertEquals("Mörfelder Landstraße".toLowerCase(), address.getStreet());
        assertEquals("362", address.getHouseNumber());
        assertEquals("60528", address.getZipcode());
        assertEquals("Frankfurt am Main".toLowerCase(), address.getCity());
    }

}
