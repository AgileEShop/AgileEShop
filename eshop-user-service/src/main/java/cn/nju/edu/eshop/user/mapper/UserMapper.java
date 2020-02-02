package cn.nju.edu.eshop.user.mapper;

import cn.nju.edu.eshop.bean.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    List<User> selectAllUser();
}
