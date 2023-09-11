package ma.enset.hopital.web;


import   org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class SecurityRestController {

    @GetMapping("/user/profile")
    // this is how we can acces directly to the authentication object
    public Authentication  authentication ( Authentication authentication) {
            System.out.println(authentication);

            //SecurityContexHolder.getContext().getAuthentication;
            return authentication;
     }

}
