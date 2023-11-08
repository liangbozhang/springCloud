package springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RefreshScope   //动态刷新
@RequestMapping("client")
public class ClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public String get() {
        return "ConfigInfo：" + configInfo;
    }
}
