/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.phonerent.test;

import au.com.phonerent.rs.RestClient;
import au.com.phonerent.rs.SmsRequestBody;

/**
 *
 * @author mac
 */
public class RestTest {
    public static void main(String [] args){
       System.out.println("REST Test");
       RestClient client = new RestClient();
       SmsRequestBody body = new SmsRequestBody();
       body.setTo("0431911088");
       body.setBody("Java Client Test");
       System.out.println(client.postWithData(body));
    }
}