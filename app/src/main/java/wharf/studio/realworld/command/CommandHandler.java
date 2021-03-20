package wharf.studio.realworld.command;

public interface CommandHandler<R, C extends Command<R>> {
	
	 R handle(C command);
}
