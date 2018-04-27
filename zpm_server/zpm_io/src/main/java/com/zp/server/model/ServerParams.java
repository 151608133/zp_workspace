package com.zp.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class ServerParams {


    @Value("${ZP.SOCKET.PORT}")
    private String port;//端口

    @Value("${protcolType}")
    private String protcolType;//协议

    @Value("${isKeepAlive}")
    private String isKeepAlive;//是否持久连接
}
