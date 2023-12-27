package com.ning.aluaback.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ning.aluaback.entity.HistoryMessage;
import com.ning.aluaback.entity.User;
import com.ning.aluaback.mapper.HistoryMapper;
import com.ning.aluaback.repository.HistoryMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Project: com.ning.aluaback.repository.impl
 * @Author: pgthinker
 * @Date: 2023/12/27 10:26
 * @Description:
 */
@Repository
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class HistoryMessageImpl implements HistoryMessageRepository {
    private final HistoryMapper historyMapper;
    @Override
    public void addMessage( HistoryMessage historyMessage) {
        historyMapper.insert(historyMessage);
    }

    @Override
    public void deleteMessage(HistoryMessage historyMessage) {
        historyMapper.deleteById(historyMessage);
    }

    @Cacheable(value = "pages", key = "'message_page_' + #user.getId() + '_' + #page + '_' + #limit")
    @Override
    public IPage<HistoryMessage> queryAllMessagePage(User user, Integer page,Integer limit) {
        Page<HistoryMessage> messagePage = new Page<>(page, limit);
        QueryWrapper<HistoryMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("u_id",user.getId());
        return historyMapper.selectPage(messagePage,queryWrapper);
    }

    @Override
    public void addMessageList(List<HistoryMessage> historyMessages) {
        historyMapper.saveHistoryMessageBatch(historyMessages);
    }

    @Override
    public void deleteAllMessage(User user, HistoryMessage historyMessage) {
        UpdateWrapper<HistoryMessage> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("u_id",user.getId());
        historyMapper.delete(updateWrapper);
    }

    @Cacheable(value = "messages", key = "'message_' + #user.getId()")
    @Override
    public List<HistoryMessage> queryAllMessage(User user) {
        QueryWrapper<HistoryMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("u_id",user.getId());
        return historyMapper.selectList(queryWrapper);
    }


}
