package cc.dividebyzero.spring.postal.parser;

import cc.dividebyzero.spring.postal.PostalAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LabelToPostalAddressConverter {
    static Logger log = LoggerFactory.getLogger(LabelToPostalAddressConverter.class);

        public static PostalAddress convert(ParserLabel[] labels){
            if(labels==null || labels.length==0){
                return null;
            }

            PostalAddress address = new PostalAddress();

            for(ParserLabel label: labels){
                log.info("parsing label>>"+label.toString());
                switch (label.getLabel().toLowerCase()){
                    case "road":
                       address.setStreet(label.getValue());
                        break;
                    case "house_number":
                        address.setHouseNumber(label.getValue());
                        break;
                    case "postcode":
                        address.setZipcode(label.getValue());
                        break;
                    case "city":
                        address.setCity(label.getValue());
                        break;
                    case "country":
                        address.setCountry(label.getValue());
                        break;
                    case "state":
                        address.setState(label.getValue());
                        break;
                }
            }
            return address;
        }
}
