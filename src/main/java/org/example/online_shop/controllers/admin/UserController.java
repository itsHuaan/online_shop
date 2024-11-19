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

import java.lang.reflect.Field;
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
        int result = userService.save(user);
        if (result == 1){
            return new ResponseEntity<>("User created", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("User creation failed", HttpStatus.CONFLICT);
    }

    @Operation(summary = "Delete Users", tags = {"01. User"})
    @DeleteMapping("/delete-user")
    public ResponseEntity<?> deleteUsers(@RequestParam long id) {
        int result = userService.delete(id);
        return result != 0
                ? new ResponseEntity<>("User deleted", HttpStatus.OK)
                : new ResponseEntity<>("Failed to delete user", HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Update Users", tags = {"01. User"})
    @PutMapping("/update-user")
    public ResponseEntity<?> updateUsersInfo(@RequestParam Long id, @RequestBody UserModel user) {
        if (userService.findById(id) == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        user.setUserId(id);
        return userService.save(user) == 2
                ? new ResponseEntity<>("User updated", HttpStatus.OK)
                : new ResponseEntity<>("Failed to update user", HttpStatus.BAD_REQUEST);
    }
}
