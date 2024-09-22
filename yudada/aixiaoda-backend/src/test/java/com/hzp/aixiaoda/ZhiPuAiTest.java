package com.hzp.aixiaoda;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hzp.aixiaoda.constant.ZhiPuAiKeyConstant;
import com.zhipu.oapi.ClientV4;
import com.zhipu.oapi.Constants;
import com.zhipu.oapi.service.v4.model.ChatCompletionRequest;
import com.zhipu.oapi.service.v4.model.ChatMessage;
import com.zhipu.oapi.service.v4.model.ChatMessageRole;
import com.zhipu.oapi.service.v4.model.ModelApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: aixiaoda-backend
 * @BelongsPackage: com.hzp.aixiaoda
 * @Author: ASUS
 * @CreateTime: 2024-08-31  16:44
 * @Description: 智普AI测试单元
 * @Version: 1.0
 */
@SpringBootTest
public class ZhiPuAiTest {

    @Resource
    private ClientV4 clientV4;


    @Test
    public void test() {
        //ClientV4 client=new ClientV4.Builder(ZhiPuAiKeyConstant.Key).build();
        List<ChatMessage> messages = new ArrayList<>();
        ChatMessage chatMessage = new ChatMessage(ChatMessageRole.USER.value(), "作为一名营销专家，请为智谱开放平台创作一个吸引人的slogan");
        messages.add(chatMessage);
        //每一个智谱Ai的每一个请求都有一个唯一id的，如果我们选择注释掉，那么系统就会随机生成一个id
//        String requestId = String.format(requestIdTemplate, System.currentTimeMillis());

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest.builder()
                .model(Constants.ModelChatGLM4)//选择什么样的大模型
                .stream(Boolean.FALSE)//是否开启流式模式  默认是false:AI会等到所有内容输出完毕后再整体返回结果  如果是true:AI会边处理边返回结果，相当于生成一个字就返回一个
                //选择调用方式：是异步调用方法还是同步调用方法
                .invokeMethod(Constants.invokeMethod)
                .messages(messages)
                .build();
        ModelApiResponse invokeModelApiResp = clientV4.invokeModelApi(chatCompletionRequest);
        System.out.println("model output:" + invokeModelApiResp.getData().getChoices().get(0));//获取AI给的第一条回答，一般情况下，AI就给一个回答
    }
}
