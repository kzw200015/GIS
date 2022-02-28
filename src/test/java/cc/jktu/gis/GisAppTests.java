package cc.jktu.gis;

import cc.jktu.gis.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GisAppTests {

    @Autowired
    private UserService userService;

    @Test
    void test() {
        System.out.println(userService.loadUserByUsername("kzw200015"));
    }

}
