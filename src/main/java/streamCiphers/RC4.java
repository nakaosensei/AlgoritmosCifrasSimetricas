package  streamCiphers;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

public class RC4 {
    private Cipher cipher;

    public RC4() throws Exception{
        cipher=Cipher.getInstance("RC4");
    }
    private static final String ENCRYPTION_ALGORITHM = "RC4";

    public byte[] encrypt(String plaintext, SecretKey secretKey, Cipher rc4) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        rc4.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] plaintextBytes = plaintext.getBytes();
        byte[] ciphertextBytes = rc4.doFinal(plaintextBytes);

        return ciphertextBytes;
    }

    public String decrypt(SecretKey secretKey, Cipher rc4, byte[] ciphertextBytes) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        rc4.init(Cipher.DECRYPT_MODE, secretKey, rc4.getParameters());
        byte[] byteDecryptedText = rc4.doFinal(ciphertextBytes);
        String plaintextBack = new String(byteDecryptedText);

        return plaintextBack;
    }

}