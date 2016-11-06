package au.com.phonerent.rs;

import javax.xml.bind.annotation.*;

/**
 * the mapping object for the token getting request
 * @author mac
 */
@XmlRootElement
public class TokenResponse {
    private String access_token;
    private String expires_in;

    /**
     * access token
     * @return string of access token
     */
    @XmlElement(name="access_token")
    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    /**
     * expires period
     * @return string of expires period
     */
    @XmlElement(name="expires_in")
    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }
    
    
}
