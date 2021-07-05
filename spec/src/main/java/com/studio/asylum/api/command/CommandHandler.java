package com.studio.asylum.api.command;

public interface CommandHandler<R, C extends Command<R>> {
	
	 R handle(C command);
}
