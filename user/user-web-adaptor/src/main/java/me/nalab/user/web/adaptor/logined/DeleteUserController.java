package me.nalab.user.web.adaptor.logined;

import lombok.RequiredArgsConstructor;
import me.nalab.user.application.port.in.UserDeleteUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class DeleteUserController {

    private final UserDeleteUseCase userDeleteUseCase;

    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@RequestAttribute("tokenValue") String token) {
        userDeleteUseCase.deleteByToken(token);
    }
}
