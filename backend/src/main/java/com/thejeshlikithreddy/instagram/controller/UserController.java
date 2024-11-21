package com.thejeshlikithreddy.instagram.controller;

import com.thejeshlikithreddy.instagram.model.User;
import com.thejeshlikithreddy.instagram.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UsersService usersService;

  @GetMapping("/allUsers")
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = usersService.getUsers();
    return ResponseEntity.ok().body(users);
  }

  @GetMapping("/{username}")
  public ResponseEntity<User> getUserById(@PathVariable String username) {
    Optional<User> user = usersService.getUserById(username);
    return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
  }

  @GetMapping("/email/{emailOrPhone}")
  public ResponseEntity<User> getUserByEmailOrPhone(@PathVariable String emailOrPhone) {
    Optional<User> user = usersService.getUserByEmailOrPhone(emailOrPhone);
    return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
  }
}