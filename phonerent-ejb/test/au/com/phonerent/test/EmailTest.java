/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.phonerent.test;

import au.com.phonerent.jma.EmailClient;

/**
 *
 * @author mac
 */
public class EmailTest {
    public static void main(String[] args) {
        EmailClient client = new EmailClient();
        System.out.println(client.sentTo("Darrenlouis15@gmail.com"));
    }
}
