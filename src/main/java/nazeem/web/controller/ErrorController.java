package nazeem.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ErrorController {

    @GetMapping("/error/{code}")
    public String handleError(@PathVariable String code) {
        switch (code) {
            case "403": case "404": case "500":
                return "error/" + code;
            default:
                return "error/500";
        }
    }
}