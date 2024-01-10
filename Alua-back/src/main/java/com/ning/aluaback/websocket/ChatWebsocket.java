package com.ning.aluaback.websocket;

import com.ning.aluaback.config.ChatConfig;
import com.ning.aluaback.listener.OpenAIWebSocketEventSourceListener;
import com.ning.aluaback.service.ChatService;
import com.ning.aluaback.service.MessageService;
import com.ning.aluaback.service.RedisService;
import com.unfbx.chatgpt.OpenAiStreamClient;
import com.unfbx.chatgpt.entity.chat.BaseMessage;
import com.unfbx.chatgpt.entity.chat.Message;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Project: com.ning.aluaback.websocket
 * @Author: pgthinker
 * @Date: 2023/12/26 23:34
 * @Description:
 */
@Component
@Slf4j
@ServerEndpoint("/api/v1/socket/{id}")
@RequiredArgsConstructor
public class ChatWebsocket {
    private static ChatService chatService;
    private static ChatConfig chatConfig;
    private static MessageService messageService;

    @Autowired
    public void setOrderService(ChatService chatService,ChatConfig chatConfig,MessageService messageService) {
        ChatWebsocket.chatService = chatService;
        ChatWebsocket.chatConfig =chatConfig;
        ChatWebsocket.messageService = messageService;
    }
    // 连接id
    private String id;
    //在线总数
    private static int onlineCount;
    //当前会话
    private Session session;
    private static final CopyOnWriteArraySet<ChatWebsocket> webSocketSet = new CopyOnWriteArraySet<>();
    /**
     * 用来存放每个客户端对应的WebSocketServer对象
     */
    private static final ConcurrentHashMap<String, ChatWebsocket> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 为了保存在线用户信息，在方法中新建一个list存储一下【实际项目依据复杂度，可以存储到数据库或者缓存】
     */
    private final static List<Session> SESSIONS = Collections.synchronizedList(new ArrayList<>());

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    /**
     * 当前连接数加一
     */
    public static synchronized void addOnlineCount() {
        ChatWebsocket.onlineCount++;
    }

    /**
     * 当前连接数减一
     */
    public static synchronized void subOnlineCount() {
        ChatWebsocket.onlineCount--;
    }
    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id){
        // 检查id
        if(chatService.checkId(id)){
            this.session = session;
            this.id = id;
            webSocketSet.add(this);
            SESSIONS.add(session);
            if(webSocketMap.containsKey(id)){
                webSocketMap.remove(id);
                webSocketMap.put(id,this);
            }else {
                webSocketMap.put(id,this);
                addOnlineCount();
            }
            log.info("[连接ID:{}] 连接连接，当前连接数:{}",this.id,getOnlineCount());
        }else{
            try {
                session.close(new CloseReason(CloseReason.CloseCodes.VIOLATED_POLICY,"Invalid ID"));
            }catch (IOException e){
                log.info("断开连接时，出现异常：{}",e.getMessage());
            }
        }
    }

    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        if (webSocketMap.containsKey(id)) {
            webSocketMap.remove(id);
            subOnlineCount();
        }
        List<Message> messageList = chatService.getMessageList(id);
        messageService.insert(chatService.getUsername(id),messageList);
        chatService.charging(id,messageList);
        chatService.deleteChatId(id);

        log.info("[连接ID:{}] 断开连接, 当前连接数:{}", id, getOnlineCount());
    }

    @OnError
    public void onError(Session session, Throwable error){
        error.printStackTrace();
        log.info("[连接ID:{}] 错误原因:{}", this.id, error.getMessage());
    }

    @OnMessage
    public void onMessage(String msg){
        if(!chatService.checkBalance(id)){
            try {
                session.getBasicRemote().sendText("积分余额不足");
                session.close(new CloseReason(CloseReason.CloseCodes.VIOLATED_POLICY,"Invalid ID"));
            }catch (IOException e){
                log.info("断开连接时，出现异常：{}",e.getMessage());
            }
        }

        // 先从redis中取出之前的message列表
        List<Message> messageList = chatService.getMessageList(id);
        if(messageList.isEmpty()){
            Message user = Message.builder().content(msg).role(BaseMessage.Role.USER).build();
            messageList.add(user);

        }else{
            if (messageList.size() > chatConfig.getChatMessageMaxLength()){
                // max: 3
                // curr: 4 [0 1 2 3]
                // => [1,2,3] => curr.size - max, max
                messageList = messageList.subList(messageList.size() - chatConfig.getChatMessageMaxLength(),chatConfig.getChatMessageMaxLength());
            }
            Message user = Message.builder().content(msg).role(BaseMessage.Role.USER).build();
            messageList.add(user);
        }
        OpenAiStreamClient streamClient = chatService.createStreamClient();
        OpenAIWebSocketEventSourceListener eventSourceListener = new OpenAIWebSocketEventSourceListener(this.session,chatService,id);
        streamClient.streamChatCompletion(messageList,eventSourceListener);
        chatService.addChatMessageList(this.id,messageList);
    }
}
