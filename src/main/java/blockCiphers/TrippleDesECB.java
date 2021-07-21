package blockCiphers;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class TrippleDesECB {
    String mode;
    String padding;

    public TrippleDesECB(String padding){
        this.mode="ECB";
        this.padding=padding;
    }

    public String encrypt(String message) throws Exception {
        final MessageDigest md = MessageDigest.getInstance("md5");
        final byte[] digestOfPassword = md.digest("HG58YZ3CR9".getBytes("utf-8"));
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8;) {
            keyBytes[k++] = keyBytes[j++];
        }
        final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
        final Cipher cipher = Cipher.getInstance("DESede/"+this.mode+"/"+this.padding);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        final byte[] plainTextBytes = message.getBytes("utf-8");
        final byte[] cipherText = cipher.doFinal(plainTextBytes);
        return org.apache.commons.codec.binary.Base64.encodeBase64String(cipherText);
    }

    public String decrypt(String msg) throws Exception {
        byte[] message = org.apache.commons.codec.binary.Base64.decodeBase64(msg);
        final MessageDigest md = MessageDigest.getInstance("md5");
        final byte[] digestOfPassword = md.digest("HG58YZ3CR9"
                .getBytes("utf-8"));
        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
        for (int j = 0, k = 16; j < 8;) {
            keyBytes[k++] = keyBytes[j++];
        }
        final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
        final Cipher decipher = Cipher.getInstance("DESede/"+this.mode+"/"+this.padding);
        decipher.init(Cipher.DECRYPT_MODE, key);
        final byte[] plainText = decipher.doFinal(message);
        return new String(plainText, "UTF-8");
    }

}
