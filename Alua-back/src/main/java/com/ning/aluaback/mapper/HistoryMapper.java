package com.ning.aluaback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ning.aluaback.entity.HistoryMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Project: com.ning.aluaback.mapper
 * @Author: pgthinker
 * @Date: 2023/12/26 15:28
 * @Description:
 */
@Mapper
public interface HistoryMapper extends BaseMapper<HistoryMessage> {
    @Insert("<script> INSERT INTO history_message (u_id,message_content,message_role,message_date) VALUES " +
            "<foreach collection='messages' item='message' separator=','>"+
            "(#{message.userId},#{message.content},#{message.role},#{message.date})" +
            "</foreach></script>")
    void saveHistoryMessageBatch(@Param("messages") List<HistoryMessage> historyMessageList);
}
