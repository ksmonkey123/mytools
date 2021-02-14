package ch.awae.mytools;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class Test {

    @GetMapping("/rest/hi")
    public Map<String, String> test(Authentication auth) {
        Map<String, String> model = new HashMap<>();
        model.put("message", "hi there, " + auth.getName());
        model.put("id", UUID.randomUUID().toString());
        return model;
    }

}