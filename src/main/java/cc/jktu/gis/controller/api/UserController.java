package cc.jktu.gis.controller.api;

import cc.jktu.gis.model.entity.UserEntity;
import cc.jktu.gis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public UserDetails getMe(Authentication authentication) {
        return ((UserDetails) authentication.getPrincipal());
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable("id") Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping("")
    public void addUser(@RequestBody UserEntity userEntity) {

    }

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable("id") Integer id) {

    }

}
