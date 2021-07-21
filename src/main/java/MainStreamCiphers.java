import streamCiphers.ChaCha20;
import streamCiphers.RC4;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class MainStreamCiphers {

    public static String[] generateValues(int qtde){
        String[] out = new String[qtde];
        Random rand = new Random();
        int i;
        for(i = 0;i<qtde;i++){
            out[i]="";
            out[i]+=rand.nextInt(10)+":"+rand.nextInt(10)+":"+rand.nextInt(10)+":"+rand.nextInt(10)+":"+rand.nextInt(10);
            System.out.println(out[i]);
        }
        return out;
    }

    public static void main(String[] args) throws Exception {
        int tamanhoArray=1000;
        RC4 rc = new RC4();
        KeyGenerator rc4KeyGenerator = KeyGenerator.getInstance("RC4");
        SecretKey secretKey = rc4KeyGenerator.generateKey();
        Cipher rc4 = Cipher.getInstance("RC4");
        String[] values = generateValues(tamanhoArray);
        long start1 = System.currentTimeMillis();
        for (String v: values){
            byte[] ciphertextBytes = rc.encrypt(v, secretKey, rc4);
            String out = rc.decrypt(secretKey, rc4, ciphertextBytes);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("RC4 "+ tamanhoArray +": "+ (end1-start1));
        ChaCha20 cipher = new ChaCha20();
        SecretKey key = getKey();
        byte[] nonce = getNonce();
        int counter = 1;
        long start2 = System.currentTimeMillis();
        for (String v:values){
            byte[] cText = cipher.encrypt(v.getBytes(), key, nonce, counter);
            byte[] pText = cipher.decrypt(cText, key, nonce, counter);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("ChaCha20 "+ tamanhoArray +": "+ (end2-start2));
    }

    private static SecretKey getKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("ChaCha20");
        keyGen.init(256, SecureRandom.getInstanceStrong());
        return keyGen.generateKey();
    }

    // 96-bit nonce (12 bytes)
    private static byte[] getNonce() {
        byte[] newNonce = new byte[12];
        new SecureRandom().nextBytes(newNonce);
        return newNonce;
    }

    private static String convertBytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte temp : bytes) {
            result.append(String.format("%02x", temp));
        }
        return result.toString();
    }
}
