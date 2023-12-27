package com.ning.aluaback.service.impl;

import com.ning.aluaback.config.ChatConfig;
import com.ning.aluaback.dto.R;
import com.ning.aluaback.dto.chat.ChatRequest;
import com.ning.aluaback.dto.chat.ChatResponse;
import com.ning.aluaback.entity.APIKey;
import com.ning.aluaback.entity.User;
import com.ning.aluaback.repository.ApiKeyRepository;
import com.ning.aluaback.repository.UserRepository;
import com.ning.aluaback.service.ChatService;
import com.ning.aluaback.service.RedisService;
import com.ning.aluaback.util.RandomStringUtils;
import com.unfbx.chatgpt.OpenAiStreamClient;
import com.unfbx.chatgpt.entity.chat.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Project: com.ning.aluaback.service.impl
 * @Author: pgthinker
 * @Date: 2023/12/26 23:49
 * @Description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {
    // 设置一天的过期时间
    private final Long timeout = 1L;
    private final RedisService redisService;
    private final ApiKeyRepository apiKeyRepository;
    private final OkHttpClient okHttpClient;
    private final ChatConfig chatConfig;
    private final UserRepository userRepository;
    @Override
    public R create(ChatRequest chatRequest) {
        String chatId = RandomStringUtils.generateRandomString(20);
        // 双向绑定
        // key - value
        // username:chatId - chatId
        // chat:chatId - username:chatId
        redisService.setWithExpiration(chatRequest.getUsername() + ":" + chatId,chatId,timeout, TimeUnit.DAYS);
        redisService.setWithExpiration("chat:"+chatId,chatRequest.getUsername()+":"+chatId,timeout,TimeUnit.DAYS);
        log.info("向redis中存入了数据：{}",redisService.get("chat:"+chatId));

        return ChatResponse.builder().chatId(chatId).status("success").build();
    }

    @Override
    public boolean checkId(String chatId) {
        return redisService.hasKey("chat:" + chatId);
    }

    @Override
    public void deleteChatId(String chatId) {
        String data = (String)redisService.get("chat:" + chatId);
        redisService.delete(data);
        redisService.delete("chat:"+chatId);
    }

    @Override
    public List<Message> getMessageList(String chatId) {
        Object o = redisService.get("messages:" + chatId);
        if(o == null){
            return new ArrayList<>();
        }else{
            return (List<Message>) redisService.get("messages:" + chatId);

        }
    }

    @Override
    public void addChatMessageList(String chatId,List<Message> messages) {
        redisService.set("messages:"+chatId, messages);
    }

    @Override
    public OpenAiStreamClient createStreamClient() {
//        Map<String, List<String>> groupList = groupApi();
//        Set<String> apis = groupList.keySet();
//        List<String> keys = new ArrayList<>();
//        String maxApi = "";
//        for(String api: apis){
//            if(groupList.get(api).size() > keys.size()){
//                keys = groupList.get(api);
//                maxApi = api;
//            }
//        }
//        return OpenAiStreamClient.builder()
//                .apiHost(maxApi)
//                .apiKey(keys)
//                .okHttpClient(okHttpClient)
//                .build();
        return OpenAiStreamClient.builder()
                .apiHost("https://api.mnzdna.xyz/")
                .apiKey(List.of("sk-cz2Q9hPT2E6gDYJH1cC90098599a4f1191F6E00069D2C38d"))
                .okHttpClient(okHttpClient)
                .build();
    }

    @Override
    public String getUsername(String chatId) {
        Object data = redisService.get("chat:" + chatId);
        log.info("====>{}",data.toString());
        if (data.toString() != null) {
            String[] result = data.toString().split(":");
            log.info("====>{}", result[0]);
            return result[0];
        }
        return null;
    }

    @Override
    public synchronized void charging(String chatId, List<Message> messages) {
        // 有几条记录，就扣多少积分
        String username = getUsername(chatId);
        User user = userRepository.findByEmail(username);
        Integer cost = messages.size() * chatConfig.getChatCost();
        Integer balance = user.getBalance();
        user.setBalance(balance - cost);
        userRepository.updateUser(user);
    }

    @Override
    public boolean checkBalance(String chatId) {
        String username = getUsername(chatId);
        User user = userRepository.findByEmail(username);
        return user.getBalance() > 0;
    }

    private Map<String,List<String>> groupApi(){
        List<APIKey> apiKeyList = apiKeyRepository.all();
        ConcurrentHashMap<String, List<String>> result = new ConcurrentHashMap<>();
        apiKeyList.forEach(item -> {
            if(result.containsKey(item.getHost())){
                List<String> group = result.get(item.getHost());
                group.add(item.getValue());
                result.put(item.getHost(),group);
            }else{
                List<String> group = new ArrayList<>();
                group.add(item.getValue());
                result.put(item.getHost(),group);
            }
        });
        return result;
    }
}
