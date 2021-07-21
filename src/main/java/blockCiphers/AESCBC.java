package blockCiphers;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AESCBC {
    private static String KEY1 = "y2A#D%kdssdkF$HU";
    private static String KEY2 = "R5yAC5ftBweJiSEv";
    private SecretKeySpec keySpec;
    private Cipher cipher;
    private IvParameterSpec ivParameterSpec;

    public AESCBC(String padding) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException {
        ivParameterSpec = new IvParameterSpec(KEY1.getBytes("UTF-8"));
        keySpec = new SecretKeySpec(KEY2.getBytes("UTF-8"), "AES");
        cipher = Cipher.getInstance("AES/CBC/"+padding);
    }

    public String encrypt(String toBeEncrypt) throws InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(toBeEncrypt.getBytes());
        return Base64.encodeBase64String(encrypted);
    }

    public String decrypt(String encrypted) throws InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.decodeBase64(encrypted));
        return new String(decryptedBytes);
    }
}
