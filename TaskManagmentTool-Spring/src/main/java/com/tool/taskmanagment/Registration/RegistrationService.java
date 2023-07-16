package com.tool.taskmanagment.Registration;

import com.tool.taskmanagment.AppUser.AppUser;
import com.tool.taskmanagment.AppUser.AppUserRole;
import com.tool.taskmanagment.AppUser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@AllArgsConstructor
@RequestMapping(path = "/registration")
public class RegistrationService {
    private final EmailValidator emailValidator;
    private final AppUserService appUserService;

    @PostMapping
    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.email());
        if (!isValidEmail) {
            throw new IllegalStateException("This email is not valid!");
        }
        String token = appUserService.singUpUser(
                new AppUser(request.firstName(),
                        request.lastName(),
                        request.email(),
                        request.password(),
                        AppUserRole.USER)
        );
        return token;
    }
}