package edu.kpdteti.backend.controller;

import edu.kpdteti.backend.ApiPath;
import edu.kpdteti.backend.model.response.user.GetUserResponse;
import edu.kpdteti.backend.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@Api
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(ApiPath.USER)
    public ResponseEntity<GetUserResponse> getUser(@RequestParam String userId)
            throws EntityNotFoundException {
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }

}
