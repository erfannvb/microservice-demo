package nvb.dev.accounts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping(path = "/sayHello")
    public String sayHello() {
        return "Hello World!";
    }

}
