package validator;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

@Service("passwordHasher")
public class PasswordHasher {
	private static int iterations = 100;// jak du�o iteracji algorytm hashuj�cy ma wykona�
										//aby by� wystarczaj�co szybki oraz trudny do z�amania przez brute force
	//private static int saltSize
	
	
	public String generatePasswordHash(String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();
         
        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return toHex(salt) + ":" + toHex(hash);
    }
     
    public byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");//"SHA1PRNG" = random number generator algorithm
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
     
    public String toHex(byte[] array) throws NoSuchAlgorithmException {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }
    
    public boolean validatePassword(String originalPassword, String storedPassword) {
        String[] parts = storedPassword.split(":");
        //int iterations = Integer.parseInt(parts[0]);
        int diff = 0;
        try {
        	byte[] salt = fromHex(parts[0]);
            byte[] hash = fromHex(parts[1]);
             
            PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] testHash = skf.generateSecret(spec).getEncoded();
            diff = hash.length ^ testHash.length;
            for(int i = 0; i < hash.length && i < testHash.length; i++){
                diff |= hash[i] ^ testHash[i];
            }
            return diff == 0;
        }catch(Exception e) {
        	e.printStackTrace();
        }
        return diff == 0;
    }
    public byte[] fromHex(String hex) throws NoSuchAlgorithmException{
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++){
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
}
