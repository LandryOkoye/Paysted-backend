package landry.paysted.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController()
@RequestMapping("/api")
public class PingController {

    @GetMapping("/ping")
    public String ping() {
        return "The application is live";
    }
    

}
