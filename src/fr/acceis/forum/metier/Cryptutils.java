package fr.acceis.forum.metier;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import java.util.Base64;

import fr.acceis.forum.models.PasswordModel;

public class Cryptutils {

	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		PasswordModel pm = hashPassword("admin");
		System.out.println(pm);
		
		pm = hashPassword("pierre");
		System.out.println(pm);
		
		pm = hashPassword("paul");
		System.out.println(pm);
		
		pm = hashPassword("jacques");
		System.out.println(pm);
		
	}
	
	public static PasswordModel hashPassword(String password) {
		
		byte[] salt = getSalt();
		String base64encoded = Base64.getEncoder().encodeToString(salt);

		String hash = getHash(password, salt);
		
		return new PasswordModel(hash, base64encoded);
	}
	
	public static boolean verifyPassword(String password, String hash, String salt) {
		
		// Salt is stored as base64
		byte[] decoded_salt = Base64.getDecoder().decode(salt);
		
		String calculatedHash = getHash(password, decoded_salt);
		
		return calculatedHash.equals(hash);
	}

	private static String getHash(String password, byte[] salt) {
		String generatedPassword = null;
	
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(salt);
			
			byte[] bytes = md.digest(password.getBytes());
			
			StringBuilder sb = new StringBuilder();
			
			for(int i=0; i< bytes.length ;i++)
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			
			generatedPassword = sb.toString();
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		return generatedPassword;
	}

	private static byte[] getSalt()
	{
		SecureRandom sr;
		try {
			sr = SecureRandom.getInstance("SHA1PRNG");
			byte[] salt = new byte[16];
			sr.nextBytes(salt);
			return salt;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

}
