package com.example.springasyncapi.service;

import com.example.springasyncapi.model.User;
import com.example.springasyncapi.repository.UserRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Async
    public CompletableFuture<List<User>> saveUser(MultipartFile file) {
        long start = System.currentTimeMillis();
        List<User> users = parseCsv(file);
        log.info("saving list of users od size{}", users.size());
        long end= System.currentTimeMillis();
        log.info("Total time: {}", end - start);
         return CompletableFuture.completedFuture(users);
    }

    @Async
    public CompletableFuture<List<User>> findAll() {
        log.info("get list of users by" + Thread.currentThread().getName());
    return CompletableFuture.completedFuture(userRepository.findAll());
    }


    private List<User> parseCsv(MultipartFile file) {
        final List<User> users = new ArrayList<>();
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                User user = new User();
                user.setName(data[0]);
                user.setEmail(data[1]);
                user.setGender(data[2]);
                users.add(user);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        userRepository.saveAll(users);
        return users;
    }

}
