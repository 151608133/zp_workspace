/**
 * 
 */
package com.zp.sys.util;

/**
 * @author: Administrator
 * @date: 2018-1-30
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ErrorStreamThread extends Thread {

    private InputStream input; // 控制台errorStream

    public ErrorStreamThread(InputStream input) {
        this.input = input;
    }

    @Override
    public void run() {
        InputStreamReader isr = null;
        BufferedReader buff = null;

        try {
            isr = new InputStreamReader(input);
            buff = new BufferedReader(isr);
            String line;
            while ((line = buff.readLine()) != null) {
                if (line.indexOf("Warning") != 0) {
                    throw new Exception(line);
                }
            }
        } catch (Exception e) {
            try {
				throw new Exception("错误流线程方法异常", e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        } finally {
            try {
                if (buff != null) {
                    buff.close();
                }
                if (isr != null) {
                    isr.close();
                }
            } catch (IOException e) {
                try {
					throw new Exception("错误流线程方法异常", e);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        }
    }
}