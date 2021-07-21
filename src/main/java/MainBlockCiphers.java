import blockCiphers.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class MainBlockCiphers {

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
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, Exception {
        int tamanhoArray=1000000;
        String[] values = generateValues(tamanhoArray);
        String encrypted="";
        String decrypted="";
        String padding="PKCS5PADDING";
        AESCBC aesCBC = new AESCBC(padding);
        long start1 = System.currentTimeMillis();
        for(String v:values){
            encrypted = aesCBC.encrypt(v);
            decrypted = aesCBC.decrypt(encrypted);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("AES CBC "+padding+" "+ tamanhoArray +": "+ (end1-start1));
        start1 = System.currentTimeMillis();
        AESGCM aesGcm = new AESGCM("NoPadding");
        for(String v:values) {
            encrypted = aesGcm.encrypt(v);
            decrypted = aesGcm.decrypt(encrypted);
        }
        end1 = System.currentTimeMillis();
        System.out.println("AES GCM "+padding+" "+ tamanhoArray +": "+ (end1-start1));

        AESCBC aesECB = new AESCBC(padding);
        start1 = System.currentTimeMillis();
        for(String v:values) {
            encrypted = aesECB.encrypt(v);
            decrypted = aesECB.decrypt(encrypted);
        }
        end1 = System.currentTimeMillis();
        System.out.println("AES ECB "+padding+" "+ tamanhoArray +": "+ (end1-start1));

        TrippleDesCBC td3= new TrippleDesCBC(padding);
        start1 = System.currentTimeMillis();
        for (String v:values){
            encrypted=td3.encrypt(v);
            decrypted=td3.decrypt(encrypted);
        }
        end1 = System.currentTimeMillis();
        System.out.println("Tripple DES CBC "+padding+" "+ tamanhoArray +": "+ (end1-start1));

        start1 = System.currentTimeMillis();
        for (String v:values) {
            TrippleDesECB td2 = new TrippleDesECB(padding);
            encrypted = td2.encrypt(v);
            decrypted = td2.decrypt(encrypted);
        }
        end1 = System.currentTimeMillis();
        System.out.println("Tripple DES ECB "+padding+" "+ tamanhoArray +": "+ (end1-start1));
    }
}
