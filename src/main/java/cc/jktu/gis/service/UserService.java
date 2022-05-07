package cc.jktu.gis.service;

import cc.jktu.gis.model.entity.UserEntity;
import cc.jktu.gis.model.exception.EntityNotFoundException;
import cc.jktu.gis.model.mapper.UserMapper;
import cc.jktu.gis.model.schema.PageResp;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final LambdaQueryWrapper<UserEntity> queryWrapper = new QueryWrapper<UserEntity>().lambda().eq(UserEntity::getUsername, username);
        final UserEntity userEntity = userMapper.selectOne(queryWrapper);
        if (userEntity == null) {
            throw new UsernameNotFoundException(MessageFormat.format("{0} not found", username));
        }

        return userEntity;
    }

    public UserEntity getUserById(Integer id) {
        final UserEntity user = userMapper.selectById(id);
        if (user == null) {
            throw new EntityNotFoundException("user", id);
        }

        return user;
    }

    public void addUser(UserEntity userEntity) {
        userMapper.insert(userEntity);
    }

    public void removeUser(Integer id) {
        userMapper.deleteById(id);
    }

    public void updateUser(Integer id, UserEntity user) {
        user.setId(id);
        userMapper.updateById(user);
    }

    public PageResp<UserEntity> getUsersByPage(Long page, Long size) {
        final Page<UserEntity> users = userMapper.selectPage(new Page<>(page, size), null);
        final PageResp<UserEntity> pageResp = new PageResp<>();
        pageResp.setTotal(users.getTotal());
        pageResp.setPage(users.getCurrent());
        pageResp.setSize(users.getSize());
        pageResp.setValues(users.getRecords());
        return pageResp;
    }

}
