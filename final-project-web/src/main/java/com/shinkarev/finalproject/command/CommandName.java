package com.shinkarev.finalproject.command;

import com.shinkarev.finalproject.command.common.ChangeLocalCommand;
import com.shinkarev.finalproject.command.common.LoginCommand;
import com.shinkarev.finalproject.command.common.LogoutCommand;
import com.shinkarev.finalproject.command.common.RegistrationCommand;
import com.shinkarev.finalproject.command.common.admin.*;

public enum CommandName {
    LOGIN(new LoginCommand()),
    CHANGE_LOCALE(new ChangeLocalCommand()),
    REGISTRATION(new RegistrationCommand()),
    SHOW_ALL_USERS(new ShowAllUsersCommand()),
    USER_INFO(new UserInfoCommand()),
    LOGOUT(new LogoutCommand()),
    USER_STATUS_CONTROL_COMMAND(new UserStatusControlCommand()),
    USER_ROLE_CONTROL_COMMAND(new UserRoleControlCommand()),
    UPDATE_USERS(new UpdateUsersCommand());

    private Command command;

    CommandName(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
