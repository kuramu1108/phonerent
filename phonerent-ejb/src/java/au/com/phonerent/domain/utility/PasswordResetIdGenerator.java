/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.phonerent.domain.utility;

import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mac
 */
public class PasswordResetIdGenerator {
    private static Random r;
    
    public static String generateId(String a) {
        String id = "Error";
        try {
            id =  Sha256.hash256(a + randomPostfix());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PasswordResetIdGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    private static String randomPostfix() {
        r = new Random(); 
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 5; i++) {
        char c = (char)(r.nextInt((int)(Character.MAX_VALUE)));
            sb.append(c);
        }
        return sb.toString();
    }
}
