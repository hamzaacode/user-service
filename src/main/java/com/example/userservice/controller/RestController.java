package com.example.userservice.controller;

import com.example.userservice.constant.RestResponseMessage;
import com.example.userservice.constant.RestResponseStatusCode;
import com.example.userservice.model.User;
import com.example.userservice.service.UserService;

import lombok.Data;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotEmpty;
import java.util.List;

import static com.example.userservice.model.RestApiResponse.buildResponseWithDetails;
import static com.example.userservice.model.RestApiResponse.buildResponseWithOutDetails;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestController {

    @NonNull
    private final UserService userService;


    @Data
    private static class UserInput {
        @NotEmpty
        String name;
    }

    @PostMapping("/save-user")
    public ResponseEntity<Object> saveUser(@RequestBody @Validated UserInput userInput) {
        userService.saveUser(userInput.name);
        return buildResponseWithOutDetails(RestResponseStatusCode.OK_STATUS, RestResponseMessage.FETCH_OK);
    }

    @GetMapping("/find-all-users")
    public ResponseEntity<Object> findAll() {
     List<User> userList=userService.findAll();
        for (User user:userList) {
            System.out.println(user.getName());
        }
        return buildResponseWithDetails(RestResponseStatusCode.OK_STATUS, RestResponseMessage.FETCH_OK, userService.findAll());
    }
}
