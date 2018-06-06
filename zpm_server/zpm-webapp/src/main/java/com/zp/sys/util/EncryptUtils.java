package com.zp.sys.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
  							
  									
public class EncryptUtils {  									
  									
   									
  									
    /// <summary>  									
  									
    /// 3des解码  									
  									
    /// </summary>  									
  									
    /// <param name="value">待解密字符串</param>  									
  									
    /// <param name="key">原始密钥字符串</param>  									
  									
    /// <returns></returns>  									
  									
    public static String Decrypt3DES(String value, String key) throws Exception {  									
  									
        byte[] b = decryptMode(GetKeyBytes(key), Base64.decode(value));  								
  									
        return new String(b);
    }  									
  									
   									
  									
    /// <summary>  									
  									
    /// 3des加密  									
  									
    /// </summary>  									
  									
    /// <param name="value">待加密字符串</param>  									
  									
    /// <param name="strKey">原始密钥字符串</param>  									
  									
    /// <returns></returns>  									
  									
    public static String Encrypt3DES(String value, String key) throws Exception {  									
  									
        String str = byte2Base64(encryptMode(GetKeyBytes(key), value.getBytes()));  								
  									
        return str;  									
  									
    }  									
  									
   									
  									
    //计算24位长的密码byte值,首先对原始密钥做MD5算hash值，再用前8位数据对应补全后8位  									
  									
    public static byte[] GetKeyBytes(String strKey) throws Exception {  									
  									
        if (null == strKey || strKey.length() < 1)  								
  									
            throw new Exception("key is null or empty!");  									
  									
   									
  									
        java.security.MessageDigest alg = java.security.MessageDigest.getInstance("MD5");  				
  									
        alg.update(strKey.getBytes());  							
  									
        byte[] bkey = alg.digest();  								
  									
        int start = bkey.length;  								
  									
        byte[] bkey24 = new byte[24];  									
  									
        for (int i = 0; i < start; i++) {  									
  									
            bkey24[i] = bkey[i];  									
  									
        }  									
  									
        for (int i = start; i < 24; i++) {//为了与	net16位key兼容  								
  									
            bkey24[i] = bkey[i - start];  									
  									
        }  									
  									
        return bkey24;  									
  									
    }  									
  									
   									
  									
    private static final String Algorithm = "DESede"; //定义 加密算法,可用 DES,DESede,Blowfish         									
  									
   									
  									
    //keybyte为加密密钥，长度为24字节  									
  									
    //src为被加密的数据缓冲区（源）    									
  									
    public static byte[] encryptMode(byte[] keybyte, byte[] src) {  									
  									
        try {  									
  									
            //生成密钥  									
  									
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm); //加密   									
  									
            Cipher c1 = Cipher.getInstance(Algorithm);  								
  									
            c1.init(Cipher.ENCRYPT_MODE, deskey);  							
  									
            return c1.doFinal(src);  								
  									
       } catch (java.security.NoSuchAlgorithmException e1) {  							
  									
            e1.printStackTrace();  								
  									
        } catch (javax.crypto.NoSuchPaddingException e2) {  							
  									
            e2.printStackTrace();  								
  									
        } catch (java.lang.Exception e3) {  							
  									
            e3.printStackTrace();  								
  									
        }  									
  									
        return null;  									
  									
    }  									
  									
   									
  									
    //keybyte为加密密钥，长度为24字节    									
  									
    //src为加密后的缓冲区  									
  									
    public static byte[] decryptMode(byte[] keybyte, byte[] src) {  									
  									
        try { //生成密钥     									
  									
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);  									
  									
            //解密       									
  									
            Cipher c1 = Cipher.getInstance(Algorithm);  								
  									
            c1.init(Cipher.DECRYPT_MODE, deskey);  							
  									
            return c1.doFinal(src);  								
  									
        } catch (java.security.NoSuchAlgorithmException e1) {  							
  									
            e1.printStackTrace();  								
  									
        } catch (javax.crypto.NoSuchPaddingException e2) {  							
  									
            e2.printStackTrace();  								
  									
        } catch (java.lang.Exception e3) {  							
  									
            e3.printStackTrace();  								
  									
        }  									
  									
        return null;  									
  									
    }  									
  									
   									
  									
    //转换成base64编码  									
  									
    public static String byte2Base64(byte[] b) {  									
  									
        return Base64.encode(b);  								
  									
    }  									
  									
   									
  									
    //转换成十六进制字符串    									
  									
    public static String byte2hex(byte[] b) {  									
  									
        String hs = "";  									
  									
        String stmp = "";  									
  									
        for (int n = 0; n < b.length; n++) {  								
  									
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));  						
  									
            if (stmp.length() == 1)  								
  									
                hs = hs + "0" + stmp;  									
  									
            else  									
  									
                hs = hs + stmp;  									
  									
            if (n < b.length - 1)  								
  									
                hs = hs + ":";  									
  									
        }  									
  									
        return hs.toUpperCase();  								
  									
    }  
    
    
    public static void main(String[] args) {  
    	System.out.println(codeToPass("hypmWQHKdOfR6D+rIQ3Qfg=="));
    	System.out.println(passToCode("Test6666"));
			
        String key = "yindatech123!@#,";  									
  									
        String password = "64TwmPW27q+izxvhQo31/Q==";  									
  									
        System.out.println("key=" + key + ",password=" + password);  							
  									
  									
        System.out.println("----------示例开始：使用java写的算法加密解密-----------");  							
  									
       try {  									
  									
            String encrypt = "";  									
  									
            String decrypt = "";  									
  									
           byte[] bkey = EncryptUtils.GetKeyBytes(key);  								
  									
            encrypt = EncryptUtils.byte2Base64(EncryptUtils.encryptMode(bkey, password.getBytes()));  						
  									
            System.out.println("用预转换密钥算加密结果=" + encrypt);  							
  									
            System.out.println("加密后base64表示=" + EncryptUtils.byte2hex(Base64.decode(encrypt)));  					
  									
            System.out.println("调用原始密钥算加密结果=" + EncryptUtils.Encrypt3DES(password, key));  						
  									
   									
  									
           try {  									
  									
                decrypt = new String(EncryptUtils.decryptMode(bkey, Base64.decode(encrypt)));  							
  									
                System.out.println("用预转换密钥算解密结果=" + decrypt);  							
  									
                System.out.println("调用原始密钥算解密结果=" + EncryptUtils.Decrypt3DES("mJOU7jIWXAY=", key));  						
  									
            } catch (Exception ex) {  									
  									
                System.out.println("Exception:" + ex.getMessage());  						
  									
            }  									
  									
        } catch (Exception ex) {  									
  									
            System.out.println("Exception:" + ex.getMessage());  						
  									
        }  									
  									
        System.out.println("----------示例结束：使用java写的算法加密解密-----------");  							
  									
    }
    
    public static String passToCode(String password) {
		String key = "yindatech123!@#,";
		String code = "";
		try {
			 byte[] bkey = EncryptUtils.GetKeyBytes(key);  								
			 code = EncryptUtils.byte2Base64(EncryptUtils.encryptMode(bkey, password.getBytes())); 
		} catch (Exception ex) {

			System.out.println("Exception:" + ex.getMessage());

		}
		return code;
	}
    
    public static String codeToPass(String code) {
		String pass = "";
		String key = "yindatech123!@#,";
		try {
			byte[] bkey = EncryptUtils.GetKeyBytes(key);
			pass = new String(EncryptUtils.decryptMode(bkey, Base64.decode(code)));;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pass;
	}
}