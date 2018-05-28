import com.zp.server.utils.ByteUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Test {

    public static void main(String args[]){
        try {
            Socket socket = new Socket("127.0.0.1",8080);

            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();

            String str= "6880AAAAAAAAAAAAAA80000E000B0031383835383236353333356D16";

            byte[] bs = ByteUtils.hexStringToByte(str);
            out.write(ByteUtils.hexStringToByte(str));

            int n  = Integer.parseInt(new String(bs));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
