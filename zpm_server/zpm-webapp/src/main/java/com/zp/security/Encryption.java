/**
 * 
 */
package com.zp.security;


import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.PBEParameterSpec;

/**
 * 加密类
 * 
 * @author tmz
 * 
 */
public class Encryption
{
	/**
	 * 加密
	 * 
	 * @param data
	 *            数据
	 * @param password
	 *            密码
	 * @param salt
	 *            盐
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String date) throws Exception
	{
		byte[] input = date.getBytes();

		Key key = KeyMes.toKey(KeyMes.password);

		PBEParameterSpec paramSpec = new PBEParameterSpec(KeyMes.initSalt(),
				100);
		Cipher cipher = Cipher.getInstance(KeyMes.ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);

		String outStr = KeyMes.encryptBASE64(cipher.doFinal(input));

		return outStr;

	}

}

