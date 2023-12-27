package com.ning.aluaback.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ning.aluaback.service.ChatService;
import com.ning.aluaback.service.MessageService;
import com.unfbx.chatgpt.entity.chat.BaseMessage;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import com.unfbx.chatgpt.entity.chat.Message;
import jakarta.websocket.Session;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Project: com.ning.aluaback.listener
 * @Author: pgthinker
 * @Date: 2023/12/26 21:40
 * @Description:
 */
@Slf4j
public class OpenAIWebSocketEventSourceListener extends EventSourceListener {
    private final Session session;
    private final StringBuffer sb = new StringBuffer();
    private final String id = "";
    private ChatService chatService;
    private String chatId;

    public OpenAIWebSocketEventSourceListener(Session session, ChatService chatService, String chatId) {
        this.session = session;
        this.chatService = chatService;
        this.chatId = chatId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onOpen(@NotNull EventSource eventSource, @NotNull Response response) {
        log.info("OpenAI建立sse连接...");
    }

    /**
     * {@inheritDoc}
     */
    @SneakyThrows
    @Override
    public void onEvent(@NotNull EventSource eventSource, String id, String type, String data) {
        if (data.equals("[DONE]")) {
            log.info("OpenAI返回数据结束了.{}",sb);
            session.getBasicRemote().sendText("[DONE]");
            List<Message> messageList = chatService.getMessageList(chatId);
            Message aiMessage = Message.builder().content(sb.toString()).role(BaseMessage.Role.ASSISTANT).build();
            messageList.add(aiMessage);
            chatService.addChatMessageList(chatId,messageList);

        }else {
            ObjectMapper mapper = new ObjectMapper();
            ChatCompletionResponse completionResponse = mapper.readValue(data, ChatCompletionResponse.class); // 读取Json
            sb.append(completionResponse.getChoices().get(0).getDelta().getContent());
            String delta = mapper.writeValueAsString(completionResponse.getChoices().get(0).getDelta());
            session.getBasicRemote().sendText(delta);
        }

    }


    @Override
    public void onClosed(@NotNull EventSource eventSource) {
        log.info("OpenAI关闭sse连接...");
    }


    @SneakyThrows
    @Override
    public void onFailure(@NotNull EventSource eventSource, Throwable t, Response response) {
        if (Objects.isNull(response)) {
            return;
        }
        ResponseBody body = response.body();
        if (Objects.nonNull(body)) {
            session.getBasicRemote().sendText(body.string());
            log.error("OpenAI  sse连接异常data：{}，异常：{}", body.string(), t);
        } else {
            log.error("OpenAI  sse连接异常data：{}，异常：{}", response, t);
        }
        eventSource.cancel();
    }

}
