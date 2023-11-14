package com.demus;

/*
 * @Author: demussong
 * @Description:
 * @Date: 2023/5/8 20:18
 */


import com.demus.model.SysUser;
import java.io.InputStream;
import org.apache.ibatis.session.SqlSession;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class UserMapperTest extends BaseMapperTest {
    @Test
//    @Ignore
    public void testUserSelectAll() {
        SqlSession sqlSession = getSqlSession();
        try {
            List<SysUser> sysUsers=sqlSession.selectList("com.demus.mapper.UserMapper.selectAll");
            for (SysUser user: sysUsers){
                System.out.println(user.getUserName());
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
//    @Ignore
    public void testUserSelectById() {
        SqlSession sqlSession = getSqlSession();
        try {
            SysUser sysUser = sqlSession.selectOne("com.demus.mapper.UserMapper.selectById", 1);
            System.out.println(sysUser.getUserName());
        } finally {
            sqlSession.close();
        }
    }

    @Test
//    @Ignore
    public void test(){
        System.out.println("");
//        InputStream inputStream = getClass().getResourceAsStream("com/demus/mapper/a.txt");
    }
}

