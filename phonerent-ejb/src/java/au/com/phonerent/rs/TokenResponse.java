/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.phonerent.rs;

import javax.xml.bind.annotation.*;

/**
 *
 * @author mac
 */
@XmlRootElement
public class TokenResponse {
    private String access_token;
    private String expires_in;

    @XmlElement(name="access_token")
    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    @XmlElement(name="expires_in")
    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }
    
    
}
