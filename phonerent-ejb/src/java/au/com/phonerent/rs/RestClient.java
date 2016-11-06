package au.com.phonerent.rs;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 * Rest client for handling the sms web service
 * @author mac
 */
@Stateless
public class RestClient {
    private final String tokenUrl = "https://api.telstra.com/v1/oauth/token";
    private final String smsUrl = "https://api.telstra.com/v1/sms/messages";
    private final String consumer_key = "qN6bxXKFgHNrAZIDvvI9zqgW1KBewVBT";
    private final String consumer_secret = "lzDCvNPAGnnLSQ8S";
    private final String scope = "SMS";
    private final String grant_type = "client_credentials";
    
    private String access_token;
    private Date initTime;
    private int expires_in;
    
    public RestClient() {
        
    }
    
    @PostConstruct
    public void init() {
        Client client = ClientBuilder.newClient();
        TokenResponse res = client.target(tokenUrl)
                .queryParam("client_id", consumer_key)
                .queryParam("client_secret", consumer_secret)
                .queryParam("scope", scope)
                .queryParam("grant_type", grant_type)
                .request(MediaType.APPLICATION_JSON)
                .get(TokenResponse.class);
                
            access_token = res.getAccess_token();
            expires_in = Integer.parseInt(res.getExpires_in());
            initTime = new Date();
        System.out.println(access_token);
        client.close();
    }
    
    /**
     * sent out a post request with body
     * @param body the body of the request
     * @return whether the request is success or not
     */
    public boolean postWithData(SmsRequestBody body) {
        if (expired()) {
            init();
        }
        Client client = ClientBuilder.newClient();
        Response result = client.target(smsUrl)
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + access_token)
                .post(Entity.json(body), Response.class);
        
        System.out.println(result.getStatus());
        client.close();
        return result.getStatus() == 202;
    }
    
    /**
     * test whether the authentication expired or not
     * @return whether the authentication expired or not
     */
    private boolean expired() {
        Date current = new Date();
        // check difference
        long up_time = current.getTime() - initTime.getTime();
        System.out.println( "up_time: " + up_time);
        System.out.println("expires_in: " + expires_in);
        return expires_in < up_time;
    }
}
