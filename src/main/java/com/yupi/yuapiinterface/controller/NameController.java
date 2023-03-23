package com.yupi.yuapiinterface.controller;

import com.yupi.yuapiclientsdk.model.User;
import com.yupi.yuapiclientsdk.utils.SignUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping("/")
    public String getNameByGet(String name) {
        return "GET 你的名字是:" + name;
    }

    @RequestMapping("/post")
    public String postNameByPost(@RequestParam String name) {
        return "POST 你的名字是:" + name;
    }

    @PostMapping("/user")
    public String getUserNameByPost(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        String accessKey = request.getHeader("accessKey");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");
        String body = request.getHeader("body");
        // todo 实际情况应该是去数据库中查是否已分配给用户
        if (!accessKey.equals("chouchou")) {
            throw new RuntimeException("无权限");
        }
        if (Long.parseLong(nonce) > 10000) {
            throw new RuntimeException("无权限");
        }
        //todo 时间和当前时间不能超过 5 分钟
        long currentTime = System.currentTimeMillis() / 1000;
        Long FIVE_MINUTES = 60 * 5L;
        if ((currentTime - Long.parseLong(timestamp)) >= FIVE_MINUTES) {
           // return handLeNoAuth(response);
        }
        //todo 实际情况中是从数据库中查出 secretKey
        String serverSign = SignUtils.genSign(body, "abcdefgh");
        if (!sign.equals(serverSign)) {
            throw new RuntimeException("无权限");
        }
        // todo 调用次数 + 1 invokeCount
        String result = "POST 用户名字是" + user.getUserName();
        return result;
    }

   /* public Mono<Void> handLeNoAuth(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }*/
}


