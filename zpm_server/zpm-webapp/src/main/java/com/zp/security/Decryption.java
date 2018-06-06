/**
 * 
 */
package com.zp.security;

/**
 * 解密类
 * @author tmz
 *
 */

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.PBEParameterSpec;

public class Decryption
{
	/**
	 * 解密
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
	public static String decrypt(String data) throws Exception
	{
		byte[] inputDate = KeyMes.decryptBASE64(data);
		Key key = KeyMes.toKey(KeyMes.password);

		PBEParameterSpec paramSpec = new PBEParameterSpec(KeyMes.initSalt(),
				100);
		Cipher cipher = Cipher.getInstance(KeyMes.ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

		return new String(cipher.doFinal(inputDate));

	}
}
