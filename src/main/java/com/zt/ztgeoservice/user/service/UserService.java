package com.zt.ztgeoservice.user.service;
import com.zt.ztgeoservice.user.dao.UserDao;
import com.zt.ztgeoservice.user.entity.User;
import com.zt.ztgeoservice.util.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserDao userDao;
    @Autowired
    private RedisTemplate redisTemplate;
    private Map<Integer,String> userInfo = new HashMap<>();
    @Value("${REDIS_KEY}")   //从配置文件中取值
    private String REDIS_KEY;
    public User userlogin(HttpServletRequest request, HttpServletResponse response, User u) {
        //字符串的序列化器
        RedisSerializer redisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        //先进行数据库查询一遍
        User us=userDao.login(u.getUsername());
        //判断us是否为空
        if(us==null){
            return null;
        }
        String pass=string2MD5(u.getPassword());
        System.out.println(pass);
        System.out.println(us.getPassword());
        if(!pass.equals(us.getPassword())){
            return null;
        }
        //定义新的token
        String token="user_"+ UUID.randomUUID().toString();
        //判断map中是否存在该id
        if(!ObjectUtils.isEmpty(userInfo.get(us.getId()))){
            //从map中获得redis中的key
            String oldToken=userInfo.get(us.getId());
            //删除redis中老的值
            redisTemplate.delete(oldToken);
        }
        //将新的的key保存到map中
        userInfo.put(us.getId(),token);
        //将信息存入redis

        redisTemplate.opsForValue().set(token,us);
        //设置redis信息过期时间
         //redisTemplate.expire(token,5*60, TimeUnit.MILLISECONDS);
        //将token放入cookie中
        CookieUtils.setCookie(request,response,"USER_TOKEN",token,5*60,true);

        return us;
    }

    public User getUserByToken(HttpServletResponse response, HttpServletRequest request) {
        User us=null;
        //从cookie中取出用户token
        String token=CookieUtils.getCookieValue(request,"USER_TOKEN");
        User o = (User)redisTemplate.opsForValue().get(token);
        return o;
    }
    /***
     * MD5加码 生成32位md5码
     */
    public  String string2MD5(String inStr){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

}

