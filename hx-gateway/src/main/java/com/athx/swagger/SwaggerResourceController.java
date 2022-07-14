package com.athx.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger.web.*;

import java.util.List;

/**
 * @author ：Hexin
 * @date ：Created in 2022/7/11 21:29
 * @description：Swagger聚合接口,Swagger-ui.html需要访问的接口
 * @modified By：
 * @version: $
 */

@RestController
@RequestMapping("/swagger-resources")
public class SwaggerResourceController {
    private SwaggerProvider swaggerProvider;

    @Autowired
    public SwaggerResourceController(SwaggerProvider swaggerProvider) {
        this.swaggerProvider = swaggerProvider;
    }

    @RequestMapping(value = "/configuration/security")
    public ResponseEntity<SecurityConfiguration> securityConfiguration() {
        return new ResponseEntity<>(SecurityConfigurationBuilder.builder().build(), HttpStatus.OK);
    }

    @RequestMapping(value = "/configuration/ui")
    public ResponseEntity<UiConfiguration> uiConfiguration() {
        return new ResponseEntity<>(UiConfigurationBuilder.builder().build(), HttpStatus.OK);
    }

    @RequestMapping
    public ResponseEntity<List<SwaggerResource>> swaggerResources() {
        return new ResponseEntity<>(swaggerProvider.get(), HttpStatus.OK);
    }

}
