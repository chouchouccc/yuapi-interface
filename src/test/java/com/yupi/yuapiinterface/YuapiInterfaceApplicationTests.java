package com.yupi.yuapiinterface;

import com.yupi.yuapiclientsdk.client.YuApiClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class YuapiInterfaceApplicationTests {

    @Resource
    private YuApiClient yuApiClient;

   /* @Test
    void contextLoads() {
        String result = yuApiClient.getNameByGet("shangchouchou");
        User user = new User();
        user.setUserName("shangyingzhi");
        String userNameByPost = yuApiClient.getUserNameByPost(user);
        System.out.println(result);
        System.out.println(userNameByPost);
    }*/

}
