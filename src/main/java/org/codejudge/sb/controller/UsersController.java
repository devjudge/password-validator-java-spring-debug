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

@RestController
@RequestMapping("/api/user")
public class UsersController {

    @Autowired
    private UserService userService;

    @PutMapping("/login/")
    @ResponseBody
    public ResponseEntity getPaginatedStudents(@RequestBody LoginRequest request) throws CustomException {
        userService.login(request);
        return new ResponseEntity<>(new SuccessResponse(), HttpStatus.OK);
    }

    @PutMapping("/change-password/")
    @ResponseBody
    public ResponseEntity changePassword(@RequestBody ChangePasswordRequest request) throws CustomException {
        userService.changePassword(request);
        return new ResponseEntity<>(new SuccessResponse(), HttpStatus.OK);
    }
}
