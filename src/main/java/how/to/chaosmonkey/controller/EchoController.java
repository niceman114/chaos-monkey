package how.to.chaosmonkey.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {
    @GetMapping("/echo/{saying}")
    public String echo(@PathVariable String saying) {
        return saying;
    }
}
