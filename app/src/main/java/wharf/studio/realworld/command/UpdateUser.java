package wharf.studio.realworld.command;

import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("user")
public class UpdateUser implements Command<UpdateUserResult> {
	
    @With
    @JsonIgnore
    private String currentUsername;
    
	@Email(message = "should be an email")
	private String email;
	
	private String username;
	
	private String password;
	
	private String image;
	
	private String bio;
}
