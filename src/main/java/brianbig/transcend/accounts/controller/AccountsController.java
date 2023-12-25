package brianbig.transcend.accounts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class AccountsController {

    @RequestMapping("/login")
    public String loginPage(){
        return "accounts/login";
    }
}
