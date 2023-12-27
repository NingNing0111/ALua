package com.ning.aluaback.repository;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ning.aluaback.entity.HistoryMessage;
import com.ning.aluaback.entity.User;

import java.util.List;

/**
 * @Project: com.ning.aluaback.repository
 * @Author: pgthinker
 * @Date: 2023/12/26 15:30
 * @Description:
 */
public interface HistoryMessageRepository {
    void addMessage(HistoryMessage historyMessage);
    void deleteAllMessage(User user,HistoryMessage historyMessage);
    List<HistoryMessage> queryAllMessage(User user);
    void deleteMessage(HistoryMessage historyMessage);
    IPage<HistoryMessage> queryAllMessagePage(User user, Integer page, Integer limit);
    void addMessageList(List<HistoryMessage> historyMessages);
}
