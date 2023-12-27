package com.ning.aluaback.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ning.aluaback.dto.R;
import com.ning.aluaback.dto.message.HistoryMessageResponse;
import com.ning.aluaback.entity.HistoryMessage;
import com.ning.aluaback.entity.User;
import com.ning.aluaback.repository.HistoryMessageRepository;
import com.ning.aluaback.repository.UserRepository;
import com.ning.aluaback.service.MessageService;
import com.unfbx.chatgpt.entity.chat.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project: com.ning.aluaback.service.impl
 * @Author: pgthinker
 * @Date: 2023/12/27 10:37
 * @Description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MessageServiceImpl implements MessageService {
    private final UserRepository userRepository;
    private final HistoryMessageRepository historyMessageRepository;
    @Override
    public R query(String username,Integer page, Integer limit) {
        User user = userRepository.findByEmail(username);
        IPage<HistoryMessage> historyMessageIPage = historyMessageRepository.queryAllMessagePage(user, page, limit);
        return HistoryMessageResponse.builder().message("查询到数据").status("success").data(historyMessageIPage).build();
    }

    @Override
    public void insert(String username, List<Message> messageList) {
        User user = userRepository.findByEmail(username);
        List<HistoryMessage> historyMessages = new ArrayList<>();
        messageList.forEach(item -> {
            historyMessages.add(HistoryMessage.builder()
                    .content(item.getContent())
                    .userId(user.getId())
                    .role(item.getRole())
                    .date(new Timestamp(System.currentTimeMillis()))
                    .build());
        });
        historyMessageRepository.addMessageList(historyMessages);
    }

}
