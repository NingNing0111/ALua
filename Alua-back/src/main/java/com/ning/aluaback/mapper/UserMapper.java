package com.ning.aluaback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ning.aluaback.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Project: com.ning.aluaback.mapper
 * @Author: pgthinker
 * @Date: 2023/12/26 15:28
 * @Description:
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
