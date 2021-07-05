package com.studio.asylum.api.command;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("user")
public class LoginUser implements Command<LoginUserResult> {
    
	@NotBlank(message = "can't be empty")
    @Email(message = "should be an email")
    private String email;
	
    @NotBlank(message = "can't be empty")
    private String password;
}
