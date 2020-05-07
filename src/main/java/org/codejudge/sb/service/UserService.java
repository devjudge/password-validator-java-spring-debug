package org.codejudge.sb.service;

import org.codejudge.sb.dao.UsersRepository;
import org.codejudge.sb.entity.Users;
import org.codejudge.sb.error.exception.CustomException;
import org.codejudge.sb.model.ChangePasswordRequest;
import org.codejudge.sb.model.LoginRequest;
import org.codejudge.sb.model.LoginSuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UsersRepository userRepo;

    public LoginSuccessResponse login(LoginRequest request) throws CustomException {
        request.validateForLogin();
        Users users = userRepo.findByCredentials(request.getEmail(), request.getPassword());
        if (null == users) {
            throw new CustomException("User not found!", HttpStatus.NOT_FOUND);
        }
        users.setIsLoggedIn(true);
        String authToken = UUID.randomUUID().toString();
        users.setAuthToken(authToken);
        userRepo.save(users);
        return new LoginSuccessResponse(authToken);
    }

    public void changePassword(ChangePasswordRequest request, HttpServletRequest httpRequest) throws CustomException {
        request.validateForChangePassword();
        String authToken = httpRequest.getHeader("auth-token");
        if (StringUtils.isEmpty(authToken)) {
            throw new CustomException("Unauthorized!", HttpStatus.UNAUTHORIZED);
        }
        Users users = userRepo.findByAuthToken(authToken);
        if (null == users) {
            throw new CustomException("Unauthorized!", HttpStatus.UNAUTHORIZED);
        }

        users.setPassword(request.getPassword());
        users.setIsLoggedIn(false);
        userRepo.save(users);
    }
}
