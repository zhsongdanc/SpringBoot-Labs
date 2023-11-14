package cn.iocoder.springboot.lab39.skywalkingdemo.controller;

import cn.iocoder.springboot.lab39.skywalkingdemo.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private JdbcTemplate template;

    @GetMapping("/mysql")
    public String mysql() {
        User user = this.selectById(45);

        System.out.println(user);
        return user.toString();
    }

    public User selectById(Integer id) {
        return template.queryForObject("SELECT id, username, password FROM tb_user WHERE id = ?",
                new BeanPropertyRowMapper<>(User.class), // 结果转换成对应的对象。Object 理论来说是 UserDO.class ，这里偷懒了。
                id);
    }

}
