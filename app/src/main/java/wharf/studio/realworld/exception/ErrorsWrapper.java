package wharf.studio.realworld.exception;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ErrorsWrapper {
	
	private Errors errors = new Errors();
	
	public void addError(String error) {
		errors.addError(error);
	}
}

@NoArgsConstructor
@Getter
final class Errors {
	
	private List<String> body = new ArrayList<String>();
	
	protected void addError(String error) {
		body.add(error);
	}
}
