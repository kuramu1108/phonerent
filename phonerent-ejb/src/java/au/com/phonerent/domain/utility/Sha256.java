package au.com.phonerent.domain.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256 {
    /**
     * encrypt string to encrypted string using sha-256
     * @param data - original string
     * @return encrypted string
     * @throws NoSuchAlgorithmException 
     */
    public static String hash256(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(data.getBytes());
        return bytesToHex(md.digest());
    }
  
    /**
     * helping method for hash256
     * @param bytes
     * @return 
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte byt : bytes) {
            result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }
}
