package com.ning.aluaback.dto.manage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ning.aluaback.dto.R;
import com.ning.aluaback.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * @Project: com.ning.aluaback.dto.manage
 * @Author: pgthinker
 * @Date: 2023/12/27 13:53
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class ManageResponseWithData extends R {
    private IPage<User> data;
    private String message;
}
