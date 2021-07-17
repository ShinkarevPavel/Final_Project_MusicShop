package com.shinkarev.finalproject.command;

import com.shinkarev.finalproject.command.common.ChangeLocalCommand;
import com.shinkarev.finalproject.command.common.LoginCommand;
import com.shinkarev.finalproject.command.common.RegistrationCommand;
import com.shinkarev.finalproject.command.common.admin.ShowAllUsersCommand;
import com.shinkarev.finalproject.command.common.admin.UpdateUsersCommand;
import com.shinkarev.finalproject.command.common.admin.UserInfoCommand;

public enum CommandName {
    LOGIN(new LoginCommand()),
    CHANGE_LOCALE(new ChangeLocalCommand()),
    REGISTRATION(new RegistrationCommand()),
    SHOW_ALL_USERS(new ShowAllUsersCommand()),
    USER_INFO(new UserInfoCommand()),
    UPDATE_USERS(new UpdateUsersCommand());

    private Command command;

    CommandName(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
