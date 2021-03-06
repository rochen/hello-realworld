package wharf.studio.realworld.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.studio.asylum.api.command.Command;
import com.studio.asylum.api.command.CommandHandler;
import com.studio.asylum.api.query.Query;
import com.studio.asylum.api.query.QueryHandler;


public abstract class AbstractController {
	
	@Autowired
	ApplicationContext app;
	
	private String resolveHandlerName(String commandName) {	
		char c[] = commandName.toCharArray();
		c[0] += 32;
		commandName  = new String(c);

		String beanName = String.format("%sHandler", commandName);
		return beanName;
	}
	
	
	@SuppressWarnings("unchecked")
	protected <R, C extends Command<R>> R executeCommand(C command) {
		String commandName = command.getClass().getSimpleName();
		String handlerName = resolveHandlerName(commandName);
		CommandHandler<R, C> commandHandler = (CommandHandler<R, C>) app.getBean(handlerName);	
		R result = commandHandler.handle(command);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	protected <R, Q extends Query<R>> R performQuery(Q query) {
		String queryName = query.getClass().getSimpleName();
		String handlerName = resolveHandlerName(queryName);
		QueryHandler<R, Q> queryHandler = (QueryHandler<R, Q>) app.getBean(handlerName);	
		R result = queryHandler.handle(query);
		return result;
	}
	
	protected Authentication getAuthentication() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication;
	}
}
