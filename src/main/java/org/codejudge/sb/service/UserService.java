package org.codejudge.sb.service;

import org.codejudge.sb.dao.UsersRepository;
import org.codejudge.sb.entity.Users;
import org.codejudge.sb.error.exception.CustomException;
import org.codejudge.sb.model.ChangePasswordRequest;
import org.codejudge.sb.model.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepository userRepo;

    public void login(LoginRequest request) throws CustomException {
        request.validateForLogin();
        Users users = userRepo.findByCredentials(request.getPassword(), request.getEmail());
        if (null == users) {
            throw new CustomException("User not found!", HttpStatus.NOT_FOUND);
        }
        users.setIsLoggedIn(true);
        userRepo.save(users);
    }

    public void changePassword(ChangePasswordRequest request) throws CustomException {
        request.validateForChangePassword();
        Users users = userRepo.findByEmail(request.getEmail());
        if (null == users) {
            throw new CustomException("User not found!", HttpStatus.NOT_FOUND);
        }
        users.setPassword(request.getPassword());
        users.setIsLoggedIn(false);
        userRepo.save(users);
    }
}
