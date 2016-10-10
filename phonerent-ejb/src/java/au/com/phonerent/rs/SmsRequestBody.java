package au.com.phonerent.rs;

import javax.xml.bind.annotation.*;

/**
 *
 * @author mac
 */
@XmlRootElement
public class SmsRequestBody {
    private String to;
    private String body;
    
    @XmlElement(name = "to")
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @XmlElement(name = "body")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
    
}
