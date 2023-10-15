package com.example.springasyncapi.controller;

import com.example.springasyncapi.model.User;
import com.example.springasyncapi.service.UserService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.CREATED)
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/users", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
            , produces = "application/json")
    public void saveUsers(
            @RequestParam(value = "files") MultipartFile[] files) {
        for (MultipartFile file: files) {
            userService.saveUser(file);
        }
    }

    @GetMapping("/users")
    public CompletableFuture<List<User>> findAll() {
        return userService.findAll();
    }
}
