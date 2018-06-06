/**
 * 
 */
package com.zp.security;

/**
 * 加密、解密示例类
 * @author tmz
 *
 */
public class SecurytyTest {
	public static void main(String[] args)
	{
		try
		{
			String inputStr = "你好，加密，解密";  
	        System.out.println("原文: " + inputStr);	   
	        
	        //加密
	        String x = Encryption.encrypt(inputStr);	  
	        System.out.println("加密后: " + x); 
	        
	        //解密
	        String outputStr = Decryption.decrypt(x); 	  
	        System.out.println("解密后: " + outputStr);  
			
			
			

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
