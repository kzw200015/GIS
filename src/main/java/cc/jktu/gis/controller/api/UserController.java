package cc.jktu.gis.controller.api;

import cc.jktu.gis.model.entity.UserEntity;
import cc.jktu.gis.model.schema.PageResp;
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

    @GetMapping("")
    public PageResp<UserEntity> getUsersByPage(@RequestParam("page") Long page, @RequestParam("size") Long size) {
        return userService.getUsersByPage(page, size);
    }

    @PostMapping("")
    public void addUser(@RequestBody UserEntity userEntity) {
        userService.addUser(userEntity);
    }

    @PatchMapping("/{id}")
    public void updateUser(@PathVariable("id") Integer id, @RequestBody UserEntity user) {
        userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable("id") Integer id) {
        userService.removeUser(id);
    }

}
