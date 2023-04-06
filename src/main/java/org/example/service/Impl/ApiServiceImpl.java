package org.example.service.Impl;

import org.example.common.Result;
import org.example.model.UserModel;
import org.example.service.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ApiServiceImpl implements ApiService {
    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);


    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 10000))
    public String callApi() throws Exception {
        logger.info("---------------------进入---------------------------");
        String url = "http://127.0.0.1:8080/add";
        UserModel userModel = new UserModel();
        userModel.setAge(33);
        userModel.setUserId(Integer.toUnsignedLong(2));
        userModel.setUserName("lisi");
        userModel.setPhone("13107168078");
        HttpEntity<UserModel> request = new HttpEntity<>(userModel);
        RestTemplate restTemplate = new RestTemplate();
        Result<String> result = restTemplate.postForObject(url,request, Result.class);
        if(result.getCode() == 200){
            return "增加成功";
        }
        throw new Exception("增加失败");
    }

    /**
     * 三次失败之后会执行此方法
     * @param e
     * @return
     */
    @Recover
    public String recover(Exception e) {
        logger.error("API 调用失败", e);
        return "调用接口失败";
    }
}
