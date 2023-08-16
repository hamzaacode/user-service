package com.example.userservice.controller;

import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Data;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

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
    public String saveUser(@RequestBody @Validated UserInput userInput) {
        userService.saveUser(userInput.name);
        return "Ok";
    }

    @GetMapping("/find-all-users")
    public ResponseEntity<List<User>> findAll() {
     List<User> userList=userService.findAll();
        for (User user:userList) {
            System.out.println(user.getName());
        }
        return ResponseEntity.ok().body(userList);
    }
}
