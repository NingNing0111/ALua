package com.ning.aluaback.dto.message;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ning.aluaback.dto.R;
import com.ning.aluaback.entity.HistoryMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * @Project: com.ning.aluaback.dto.message
 * @Author: pgthinker
 * @Date: 2023/12/27 10:46
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class HistoryMessageResponse extends R {
    IPage<HistoryMessage> data;
    String message;
}
