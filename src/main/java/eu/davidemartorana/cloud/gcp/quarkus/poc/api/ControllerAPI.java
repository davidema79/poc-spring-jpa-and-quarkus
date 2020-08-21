package eu.davidemartorana.cloud.gcp.quarkus.poc.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerAPI {

    @GetMapping("/hello")
    public String getHelloMsg(@RequestParam("name") final String name, @RequestParam("lastName") final String lastName) {
        StringBuilder sb = new StringBuilder("Hello World");

        if(StringUtils.isNotEmpty(name)) {
            sb
                .append(" ")
                .append(name);
        }

        if(StringUtils.isNotEmpty(lastName)) {
            sb
                    .append(" ")
                    .append(lastName);
        }

        return sb.append("!").toString();
    }


}
