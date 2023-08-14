package com.example.userservice.controller;

import com.example.userservice.service.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Data;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RestController {

    @NonNull
    private final UserService userService;


    @Data
    private static class UserInput {
        @NotBlank
        Integer id;
        @NotEmpty
        String name;
    }

    @PostMapping("/save-user")
    public String saveUser(@RequestBody @Validated UserInput userInput) {
        userService.saveUser(userInput.name);
        return "Ok";
    }
}
