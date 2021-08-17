package com.shinkarev.finalproject.command;

import com.shinkarev.musicshop.entity.UserRoleType;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumSet;
import java.util.Optional;

import static com.shinkarev.finalproject.command.ParamName.COMMAND_PARAM;

/**
 * Command provider get name of command from request.
 */

public class CommandProvider {
    private static Logger logger = LogManager.getLogger();

    private static final String UNKNOWN_COMMAND = "Unknown command: ";

    private final static CommandProvider instance = new CommandProvider();

    private CommandProvider() {
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public EnumSet<UserRoleType> getCommandAccessLevel(HttpServletRequest request) {
        return getCommandName(request).get().getUserRole();
    }

    /**
     *
     * @param request is HttpServletRequest request
     * @return Optional with command from request
     */

    public Optional<CommandName> getCommandName(HttpServletRequest request) {
        String name = request.getParameter(COMMAND_PARAM);
        CommandName commandName;
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException ex) {
            logger.log(Level.DEBUG, UNKNOWN_COMMAND + name);
            commandName = CommandName.WRONG_COMMAND;
        }
        return Optional.of(commandName);
    }
}
