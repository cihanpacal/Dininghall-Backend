package com.cihanpacal.dininghall.validation;


import com.cihanpacal.dininghall.error.*;
import com.cihanpacal.dininghall.model.entity.Stock;
import com.cihanpacal.dininghall.model.entity.User;
import com.cihanpacal.dininghall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserValidatorImpl implements UserValidator{

    private final UserRepository userRepository;

    @Override
    public void checkUserAlreadyExist(String email) {
        Optional<User> optionalUser=userRepository
                .findByEmail(email);
        optionalUser.ifPresent((user)->{
            throw new UserAlreadyExistException(
                    "exception.UserAlreadyExistException"
            );
        });
    }

    @Override
    public User checkUserExistByEmail(String email) {
        Optional<User> optionalUser=userRepository.findByEmail(email);
        User user=optionalUser.orElseThrow(()->{
            throw new ResourceNotFoundException("exception.UserNotFoundException",new Object[]{});
        });
        return user;
    }

    @Override
    public void checkUserAlreadyConfirmed(User user) {
        if(user.getEnabled()){
            throw new UserAlreadyConfirmedException("exception.UserAlreadyConfirmed");
        }
    }

    @Override
    public void checkUserNotConfirmed(User user) {
        if(!user.getEnabled()){
            throw new UserNotConfirmedException("exception.UserNotConfirmedException");
        }
    }

    @Override
    public User checkUserExistById(Long id) {
        Optional<User> optionalUser=userRepository.findById(id);
        User user=optionalUser.orElseThrow(()->{
            throw new ResourceNotFoundException("exception.ResourceNotFoundException",new Object[] {id,"Kullanıcı"});
        });
        return user;
    }

}
