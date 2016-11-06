package au.com.phonerent.rs;

import javax.xml.bind.annotation.*;

/**
 * mapping class for the sms post request body
 * @author mac
 */
@XmlRootElement
public class SmsRequestBody {
    private String to;
    private String body;
    
    /**
     * the recipient of the sms
     * @return string of recipient's number
     */
    @XmlElement(name = "to")
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    /**
     * the body of the sms
     * @return string of sms body
     */
    @XmlElement(name = "body")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
    
}
