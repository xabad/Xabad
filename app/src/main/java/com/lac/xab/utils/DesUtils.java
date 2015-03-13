// +----------------------------------------------------------------------
// | FileName:   @DesUtils.java  
// +----------------------------------------------------------------------
// | CreateTime: @2014-9-24  @上午11:54:47
// +----------------------------------------------------------------------
// | Copyright:  http://www.xueyong.net.cn
// +----------------------------------------------------------------------
// | Author:     xab(admin@xueyong.net.cn)
// +----------------------------------------------------------------------
package com.lac.xab.utils;

import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;
public class DesUtils {

	private byte[] desKey;

	public DesUtils(String desKey) {
		this.desKey = desKey.getBytes();
	}
	public byte[] desEncrypt(byte[] plainText) throws Exception {
		SecureRandom sr = new SecureRandom();
		byte rawKeyData[] = desKey;
		DESKeySpec dks = new DESKeySpec(rawKeyData);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, key, sr);
		byte data[] = plainText;
		byte encryptedData[] = cipher.doFinal(data);
		return encryptedData;
	}

	public byte[] desDecrypt(byte[] encryptText) throws Exception {
		SecureRandom sr = new SecureRandom();
		byte rawKeyData[] = desKey;
		DESKeySpec dks = new DESKeySpec(rawKeyData);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, key, sr);
		byte encryptedData[] = encryptText;
		byte decryptedData[] = cipher.doFinal(encryptedData);
		return decryptedData;
	}

	public String encrypt(String input) throws Exception {
		return base64Encode(desEncrypt(input.getBytes()));
	}

	public String decrypt(String input) throws Exception {
		byte[] result = base64Decode(input);
		return new String(desDecrypt(result));
	}

	public static String base64Encode(byte[] s) {
		if (s == null)
			return null;
		
		return Base64.encodeBase64String(s);
	}

	public static byte[] base64Decode(String s) throws IOException {
		if (s == null)
			return null;
		return Base64.decodeBase64(s.getBytes());
	}

	public static String xabencrypt(String input) throws Exception{
		String key = "a1b2c3d4e5f6g7h8i9g8k7m6l5n4o3p2q1r0";
		DesUtils crypt = new DesUtils(key);
		return crypt.encrypt(input);
	}
	public static String xabdecrypt(String input){
		String result="";
		String key = "a1b2c3d4e5f6g7h8i9g8k7m6l5n4o3p2q1r0";
		DesUtils crypt = new DesUtils(key);
		try {
			result= crypt.decrypt(input);
		} catch (Exception e) {
			result="";
		}
		return result;
	}
	public static void main(String[] args) throws Exception {
		String key = "a1b2c3d4e5f6g7h8i9g8k7m6l5n4o3p2q1r0";
		String input = "come";
		DesUtils crypt = new DesUtils(key);
		System.out.println("Encode:" + crypt.encrypt(input));
		System.out.println("Decode:" + crypt.decrypt(crypt.encrypt(input)));
	}
	
}