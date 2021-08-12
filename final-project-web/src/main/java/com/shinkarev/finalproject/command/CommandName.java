package com.shinkarev.finalproject.command;

import com.shinkarev.finalproject.command.client.*;
import com.shinkarev.finalproject.command.common.*;
import com.shinkarev.finalproject.command.admin.*;

public enum CommandName {
    LOGIN(new LoginCommand()),
    CHANGE_LOCALE(new ChangeLocalCommand()),
    REGISTRATION(new RegistrationCommand()),
    SHOW_ALL_USERS(new ShowAllUsersCommand()),
    USER_INFO(new UserInfoCommand()),
    LOGOUT(new LogoutCommand()),
    USER_STATUS_CONTROL_COMMAND(new UserStatusControlCommand()),
    USER_ROLE_CONTROL_COMMAND(new UserRoleControlCommand()),
    ADD_INSTRUMENT_COMMAND(new AddInstrumentCommand()),
    SHOW_TYPE_INSTRUMENT(new ShowInstrumentType()),
    INSTRUMENT_STATUS_CONTROL_COMMAND(new InstrumentStatusControlCommand()),
    INSTRUMENT_TYPE_CONTROL_COMMAND(new InstrumentTypeControlCommand()),
    REGISTRATION_CONFIRMATION_COMMAND(new RegistrationConfirmationCommand()),
    SHOW_ALL_INSTRUMENTS_COMMAND(new ShowAllInstrumentsCommand()),
    FIND_USER_COMMAND(new FindUserCommand()),
    SHOW_INSTRUMENT_BY_TYPE_COMMAND(new ShowInstrumentByTypeCommand()),
    SHOW_INSTRUMENT_DETAILS_COMMAND(new ShowInstrumentDetailsCommand()),
    SET_INSTRUMENT_RATING_COMMAND(new SetInstrumentRatingCommand()),
    CHECK_CART_COMMAND(new CheckCartCommand()),
    REMOVE_FROM_CART_COMMAND(new RemoveFromCartCommand()),
    ORDER_PROCESSING_COMMAND(new OrderProcessingCommand()),
    QUANTITY_CONTROL_COMMAND(new QuantityControlCommand()),
    CREATE_ORDER_COMMAND(new CreateOrderCommand()),
    FIND_ORDER_COMMAND(new FindOrderCommand()),
    SHOW_ORDER_DETAILS_COMMAND(new ShowOrderDetailsCommand()),
    CHANGE_PASSWORD_COMMAND(new ChangePasswordCommand()),
    EDIT_PROFILE_COMMAND(new EditProfileCommand()),
    ADD_TO_BUCKET_COMMAND(new AddToBucketCommand());

    private Command command;

    CommandName(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
