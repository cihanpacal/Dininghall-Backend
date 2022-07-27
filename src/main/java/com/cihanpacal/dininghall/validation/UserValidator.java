package com.cihanpacal.dininghall.validation;

import com.cihanpacal.dininghall.model.entity.User;

public interface UserValidator {
    void checkUserAlreadyExist(String email);
    User checkUserExistByEmail(String email);
    void checkUserAlreadyConfirmed(User user);
    void checkUserNotConfirmed(User user);
    User checkUserExistById(Long id);
}
