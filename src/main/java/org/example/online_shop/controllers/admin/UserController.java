package org.example.online_shop.controllers.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.online_shop.dto.UserDto;
import org.example.online_shop.models.UserModel;
import org.example.online_shop.services.impl.UserService;
import org.example.online_shop.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "01. User")
@RestController
@RequestMapping(value = Const.API_PREFIX + "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get Users", tags = {"01. User"})
    @GetMapping("/get-user")
    public ResponseEntity<?> getUsers() {
        List<UserDto> result = userService.findAll();
        return result != null && !result.isEmpty()
                ? new ResponseEntity<>(result, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Create New User", tags = {"01. User"})
    @PostMapping("create-user")
    public ResponseEntity<?> createUser(@RequestBody UserModel user) {
        boolean isExisting = userService.findByEmailOrUsername(user.getEmail(), user.getUsername()) != null;
        if (isExisting) {
            return new ResponseEntity<>("Email or Username has been taken by another user", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }
}
