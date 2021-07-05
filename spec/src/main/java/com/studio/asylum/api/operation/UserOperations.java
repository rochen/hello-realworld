package com.studio.asylum.api.operation;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.studio.asylum.api.command.LoginUser;
import com.studio.asylum.api.command.LoginUserResult;
import com.studio.asylum.api.command.RegisterUser;
import com.studio.asylum.api.command.RegisterUserResult;
import com.studio.asylum.api.command.UpdateUser;
import com.studio.asylum.api.command.UpdateUserResult;
import com.studio.asylum.api.query.GetCurrentUserResult;


public interface UserOperations {

    @PostMapping("/users/login")
    ResponseEntity<LoginUserResult> login(@Valid @RequestBody LoginUser command);

    @PostMapping("/users")
    ResponseEntity<RegisterUserResult> register(@Valid @RequestBody RegisterUser command);

    @GetMapping("/user")
    ResponseEntity<GetCurrentUserResult> current();

    @PutMapping("/user")
    ResponseEntity<UpdateUserResult> update(@Valid @RequestBody UpdateUser command);

}
