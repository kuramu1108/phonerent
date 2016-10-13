/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.phonerent.test;

import au.com.phonerent.rs.RestClient;
import au.com.phonerent.rs.SmsRequestBody;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mac
 */
public class RestTest {
    public static void main(String [] args){
       System.out.println("REST Test");
       RestClient client = new RestClient();
       client.init();
       SmsRequestBody body = new SmsRequestBody();
       body.setTo("0431911088");
       body.setBody("Java Client Test");
       System.out.println(client.postWithData(body));
        try {
            Thread.sleep(3000);
            SmsRequestBody body22 = new SmsRequestBody();
       body22.setTo("0431911088");
       body22.setBody("Java Client Test 22");
       System.out.println(client.postWithData(body22));
        } catch (InterruptedException ex) {
            Logger.getLogger(RestTest.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}