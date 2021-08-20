package com.shinkarev.finalproject.validator;

import com.shinkarev.finalproject.validator.Impl.*;

public class ValidatorProvider {
    public static final InputDataValidator ADMIN_REGISTRATION_VALIDATOR = AdminRegistrationValidatorImpl.getInstance();
    public static final InputDataValidator CHANGE_PASSWORD_VALIDATOR = ChangePasswordValidatorImpl.getInstance();
    public static final InputDataValidator EDIT_INSTRUMENT_VALIDATOR = EditInstrumentValidatorImpl.getInstance();
    public static final InputDataValidator EDIT_PROFILE_VALIDATOR = EditProfileValidatorImpl.getInstance();
    public static final InputDataValidator INSTRUMENT_CREATION_VALIDATOR = InstrumentCreationValidator.getInstance();
    public static final InputDataValidator REGISTRATION_VALIDATOR = RegistrationValidatorImp.getInstance();
    public static final InputDataValidator FORGOT_PASSWORD_VALIDATOR = ForgotPasswordValidator.getInstance();
    public static final InputDataValidator ORDER_VALIDATOR = OrderCreationValidator.getInstance();

}
