package blockCiphers;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

public class AESECB {
    private Cipher cipher;
    private final SecureRandom secureRandom = new SecureRandom();
    private final static int GCM_IV_LENGTH = 12;
    private static String KEY1 = "UkXp2s5v8x/A?D(G";
    private SecretKey secretKey;


    public AESECB(String padding) throws Exception {
        cipher = Cipher.getInstance("AES/ECB/"+padding);
        secretKey = new SecretKeySpec(KEY1.getBytes("UTF-8"), "AES");
    }

    public String encrypt(String plaintext) throws Exception {
        byte[] iv = new byte[GCM_IV_LENGTH];
        secureRandom.nextBytes(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] cipherText = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        ByteBuffer byteBuffer = ByteBuffer.allocate(iv.length + cipherText.length);
        byteBuffer.put(iv);
        byteBuffer.put(cipherText);
        return org.apache.commons.codec.binary.Base64.encodeBase64String(byteBuffer.array());
    }

    public String decrypt(String encrypted) throws Exception {
        byte[] cipherMessage = org.apache.commons.codec.binary.Base64.decodeBase64(encrypted);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] plainText = cipher.doFinal(cipherMessage, GCM_IV_LENGTH, cipherMessage.length - GCM_IV_LENGTH);
        return new String(plainText, StandardCharsets.UTF_8);
    }
}

