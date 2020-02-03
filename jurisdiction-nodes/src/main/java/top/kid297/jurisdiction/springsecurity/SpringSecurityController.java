package top.kid297.jurisdiction.springsecurity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringSecurityController {

    @RequestMapping("/")
    public String home(){
        return "hello spring boot";
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello word";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping("/roleAuth")
    public String role(){
        return "admin auth";
    }
}
