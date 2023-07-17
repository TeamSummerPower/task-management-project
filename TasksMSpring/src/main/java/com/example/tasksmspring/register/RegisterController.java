package com.example.tasksmspring.register;

import com.example.tasksmspring.users.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user/registration")
public class RegisterController {
    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }
    @PostMapping(path = "/user/validRegistration")
    public User registration(@RequestBody Register request){
        return registerService.register(request);
    }
}
