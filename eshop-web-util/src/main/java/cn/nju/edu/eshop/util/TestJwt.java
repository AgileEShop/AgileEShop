package cn.nju.edu.eshop.util;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.impl.Base64UrlCodec;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestJwt {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("memberId", "1");
        map.put("nickname", "zhangsan");
        String ip = "127.0.0.1";
        String time = new SimpleDateFormat("yyyyMMdd HHmm").format(new Date());
        String encode = JwtUtil.encode("agileeshop", map, ip + time);
        System.err.println(encode);
        // String tokenUserInfo = StringUtils.substringBetween(encode, ".");
        Base64UrlCodec base64UrlCodec = new Base64UrlCodec();
        byte[] tokenBytes = base64UrlCodec.decode("eyJuaWNrbmFtZSI6InpoYW5nc2FuIiwibWVtYmVySWQiOiIxIn0");
        String tokenJson = null;
        tokenJson = new String(tokenBytes, StandardCharsets.UTF_8);
        Map map1 = JSON.parseObject(tokenJson, Map.class);
        System.out.println("64=" + map1);
    }
}
