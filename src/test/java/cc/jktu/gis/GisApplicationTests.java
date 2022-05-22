package cc.jktu.gis;

import cc.jktu.gis.model.constant.Gender;
import cc.jktu.gis.model.constant.Role;
import cc.jktu.gis.model.entity.AccidentEntity;
import cc.jktu.gis.model.entity.PoliceEntity;
import cc.jktu.gis.model.entity.UserEntity;
import cc.jktu.gis.model.mapper.AccidentMapper;
import cc.jktu.gis.model.mapper.PoliceMapper;
import cc.jktu.gis.model.mapper.UserMapper;
import com.github.javafaker.Faker;
import org.postgis.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

//@SpringBootTest
class GisApplicationTests {

    @Autowired
    private PoliceMapper policeMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AccidentMapper accidentMapper;

    void generatePassword() {
        System.out.println(new BCryptPasswordEncoder().encode("1234abc"));
    }


    void generateData() {
        generateUsers();
        generateAccidents();
        generatePolices();
    }


    void generateUsers() {
        final Faker faker = new Faker(new Locale("zh-CN"));
        final List<UserEntity> users = new ArrayList<>();
        for (int i = 3; i <= 10; i++) {
            final UserEntity user = new UserEntity();
            user.setId(i);
            user.setUsername(faker.name().name());
            user.setPassword(faker.crypto().md5());
            user.setRole(Role.POLICE);
            users.add(user);
        }
        users.forEach(u -> userMapper.insert(u));
    }


    void generateAccidents() {
        final Faker faker = new Faker(new Locale("zh-CN"));
        final ArrayList<AccidentEntity> accidents = new ArrayList<>();
        for (int i = 1; i <= 500; i++) {
            final AccidentEntity accident = new AccidentEntity();
            accident.setId(i);
            accident.setCoordinate(new Point(120.5D + (121D - 120.5D) * faker.random().nextDouble(), 31D + (31.7D - 31D) * faker.random().nextDouble()));
            accident.setDescription("事故描述" + i);
            accident.setImage(String.format("/img/%s.jpg", i));
            accident.setTime(faker.date().past(3 * 365, TimeUnit.DAYS).toInstant());
            accident.setIsResolved(faker.random().nextBoolean());
            accidents.add(accident);
        }

        accidents.forEach(accident -> accidentMapper.insert(accident));
    }


    void generatePolices() {
        final Faker faker = new Faker(new Locale("zh-CN"));
        final ArrayList<PoliceEntity> polices = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            final PoliceEntity police = new PoliceEntity();
            police.setId(i);
            police.setUserId(i + 2);
            police.setGender(faker.random().nextBoolean() ? Gender.MALE : Gender.FEMALE);
            police.setIsWorking(faker.random().nextBoolean());
            police.setPhone(faker.phoneNumber().phoneNumber());
            police.setName(faker.name().name());
            polices.add(police);
        }

        polices.forEach(police -> policeMapper.insert(police));
    }

}
