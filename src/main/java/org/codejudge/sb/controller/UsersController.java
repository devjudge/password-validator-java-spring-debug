package org.codejudge.sb.controller;

import org.codejudge.sb.error.exception.CustomException;
import org.codejudge.sb.model.ChangePasswordRequest;
import org.codejudge.sb.model.LoginRequest;
import org.codejudge.sb.model.SuccessResponse;
import org.codejudge.sb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpRequest;

    @PutMapping("/login/")
    @ResponseBody
    public ResponseEntity login(@RequestBody LoginRequest request) throws CustomException {
        return new ResponseEntity<>(userService.login(request), HttpStatus.OK);
    }

    @PutMapping("/change-password/")
    @ResponseBody
    public ResponseEntity changePassword(@RequestBody ChangePasswordRequest request) throws CustomException {
        userService.changePassword(request, httpRequest);
        return new ResponseEntity<>(new SuccessResponse(), HttpStatus.OK);
    }
}
