package au.com.phonerent.domain.utility;

import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * password reset id generator
 * @author mac
 */
public class PasswordResetIdGenerator {
    private static Random r;
    
    /**
     * generate a password reset id
     * @param a the email of the account
     * @return password reset id
     */
    public static String generateId(String a) {
        String id = "Error";
        try {
            id =  Sha256.hash256(a + randomPostfix());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PasswordResetIdGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    /**
     * generate a random postfix for the source
     * @return postfix string
     */
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
