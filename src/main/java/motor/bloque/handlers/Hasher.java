package motor.bloque.handlers;

import motor.bloque.exceptions.NoSuchCard;
import motor.bloque.interfaces.Card;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class Hasher {

    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static Map<String, String> hashNewPassword(String passwordToHash){
        try {
            byte[] salt = getSalt();
            String securePassword = getSecurePassword(passwordToHash, salt);
            Map<String, String> map = new HashMap<>();
            map.put("password", securePassword);
            map.put("salt", bytesToHex(salt));
            return map;
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }

    public static boolean validatePassword(String password, int cardNumber) throws NoSuchCard{
        Card card = Persistence.getCard(cardNumber);
        String salt = card.getSalt();

        String toValidate = getSecurePassword(password, salt.getBytes());
        if (toValidate != null) return toValidate.equals(card.getHashedPIN());
        return false;
    }

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    private static String getSecurePassword(String passwordToHash, byte[] salt)
    {
        byte[] generatedPassword;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            generatedPassword = md.digest(passwordToHash.getBytes());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }
        return bytesToHex(generatedPassword);
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

}
