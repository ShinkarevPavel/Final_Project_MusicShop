package com.shinkarev.finalproject.command;

import com.shinkarev.finalproject.command.client.*;
import com.shinkarev.finalproject.command.common.*;
import com.shinkarev.finalproject.command.admin.*;
import com.shinkarev.musicshop.entity.UserRoleType;

import java.util.EnumSet;

import static com.shinkarev.musicshop.entity.UserRoleType.*;


public enum CommandName {
    LOGIN(new LoginCommand(), EnumSet.of(ANONYMOUS)),
    TO_MAIN_PAGE(new ToMainPageCommand(), EnumSet.of(ADMIN, CLIENT)),
    TO_LOGIN_PAGE_COMMAND(new ToLoginPageCommand(), EnumSet.of(ADMIN, CLIENT, GUEST, ANONYMOUS)),
    TO_REGISTRATION_PAGE_COMMAND(new ToRegistrationPageCommand(), EnumSet.of(ADMIN, CLIENT, GUEST, ANONYMOUS)),
    CHANGE_LOCALE(new ChangeLocalCommand(), EnumSet.of(ADMIN, CLIENT, GUEST, ANONYMOUS)),
    REGISTRATION(new RegistrationCommand(), EnumSet.of(ANONYMOUS)),
    SHOW_ALL_USERS(new ShowAllUsersCommand(), EnumSet.of(ADMIN)),
    USER_INFO(new UserInfoCommand(), EnumSet.of(ADMIN)),
    LOGOUT(new LogoutCommand(), EnumSet.of(ADMIN, CLIENT)),
    USER_STATUS_CONTROL_COMMAND(new UserStatusControlCommand(), EnumSet.of(ADMIN)),
    USER_ROLE_CONTROL_COMMAND(new UserRoleControlCommand(), EnumSet.of(ADMIN)),
    ADD_USER_COMMAND(new AddUserCommand(), EnumSet.of(ADMIN)),
    ADD_INSTRUMENT_COMMAND(new AddInstrumentCommand(), EnumSet.of(ADMIN)),
    SHOW_TYPE_INSTRUMENT(new ShowInstrumentTypeCommand(), EnumSet.of(ADMIN)),
    INSTRUMENT_STATUS_CONTROL_COMMAND(new InstrumentStatusControlCommand(), EnumSet.of(ADMIN)),
    INSTRUMENT_TYPE_CONTROL_COMMAND(new InstrumentTypeControlCommand(), EnumSet.of(ADMIN)),
    REGISTRATION_CONFIRMATION_COMMAND(new RegistrationConfirmationCommand(), EnumSet.of(GUEST)),
    SHOW_ALL_INSTRUMENTS_COMMAND(new ShowAllInstrumentsCommand(), EnumSet.of(ADMIN)),
    FIND_USER_COMMAND(new FindUserCommand(), EnumSet.of(ADMIN)),
    SHOW_INSTRUMENT_BY_TYPE_COMMAND(new ShowInstrumentByTypeCommand()),
    SHOW_INSTRUMENT_DETAILS_COMMAND(new ShowInstrumentDetailsCommand()),
    SET_INSTRUMENT_RATING_COMMAND(new SetInstrumentRatingCommand()),
    CHECK_CART_COMMAND(new CheckCartCommand(), EnumSet.of(ADMIN, CLIENT)),
    REMOVE_FROM_CART_COMMAND(new RemoveFromCartCommand(), EnumSet.of(ADMIN, CLIENT)),
    ORDER_PROCESSING_COMMAND(new OrderProcessingCommand(), EnumSet.of(ADMIN, CLIENT)),
    QUANTITY_CONTROL_COMMAND(new QuantityControlCommand(),EnumSet.of(ADMIN, CLIENT)),
    CREATE_ORDER_COMMAND(new CreateOrderCommand(), EnumSet.of(ADMIN, CLIENT)),
    FIND_ORDER_COMMAND(new FindOrderCommand(), EnumSet.of(ADMIN, CLIENT)),
    SHOW_ORDER_DETAILS_COMMAND(new ShowOrderDetailsCommand(), EnumSet.of(ADMIN, CLIENT)),
    CHANGE_PASSWORD_COMMAND(new ChangePasswordCommand(), EnumSet.of(ADMIN, CLIENT)),
    EDIT_PROFILE_COMMAND(new EditProfileCommand(), EnumSet.of(ADMIN, CLIENT)),
    SHOW_ALL_ORDERS_COMMAND(new ShowAllOrdersCommand(), EnumSet.of(ADMIN)),
    CHANGE_ORDER_STATUS_COMMAND(new ChangeOrderStatusCommand(), EnumSet.of(ADMIN)),
    FIND_ORDER_BY_TYPE(new FindOrderByType(), EnumSet.of(ADMIN)),
    UPDATE_INSTRUMENT_COMMAND(new UpdateInstrumentCommand(), EnumSet.of(ADMIN)),
    SAVE_UPDATED_INSTRUMENT_COMMAND(new SaveUpdatedInstrumentCommand(), EnumSet.of(ADMIN)),
    ADD_TO_BUCKET_COMMAND(new AddToBucketCommand(), EnumSet.of(ADMIN, CLIENT)),
    BY_NOW_COMMAND(new ByNowCommand(), EnumSet.of(ADMIN, CLIENT)),
    WRONG_COMMAND(new WrongCommand());

    private Command command;
    private EnumSet<UserRoleType> userRole;

    CommandName(Command command) {
        this.command = command;
        userRole = EnumSet.of(ADMIN, CLIENT, GUEST, ANONYMOUS);
    }

    CommandName(Command command, EnumSet<UserRoleType> userRole) {
        this.command = command;
        this.userRole = userRole;

    }

    public Command getCommand() {
        return command;
    }

    public EnumSet<UserRoleType> getUserRole() {
        return userRole;
    }
}
