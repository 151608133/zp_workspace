package com.zp.server.inter;

import java.io.InputStream;
import java.io.OutputStream;

public interface Channel {

    InputStream getInputStream();

    OutputStream getOutputStream();

    String getURl();

    String getRemoteUrl();

    boolean isAlive();

    String getRemoteIpAddress();

    void close();

}
