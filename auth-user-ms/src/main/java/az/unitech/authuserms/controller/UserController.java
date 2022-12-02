package az.unitech.authuserms.controller;

import az.unitech.authuserms.dtos.dto.UserDto;
import az.unitech.authuserms.dtos.response.CommonResponse;
import az.unitech.authuserms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<CommonResponse<List<UserDto>>> getAllUsers() {
        return ResponseEntity.ok(CommonResponse.success(userService.getAllUser()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<UserDto>> getUserById(@PathVariable Long id) {
        var userDto = userService.getUserById(id);
        return ResponseEntity.ok(CommonResponse.success(userDto));
    }

    @PostMapping
    public ResponseEntity<CommonResponse<?>> saveUser(@Valid @RequestBody UserDto userDto) {
        userService.saveUser(userDto);
        return ResponseEntity.ok(CommonResponse.success("User was added successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<?>> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(CommonResponse.success("User was deleted successfully"));
    }
}
