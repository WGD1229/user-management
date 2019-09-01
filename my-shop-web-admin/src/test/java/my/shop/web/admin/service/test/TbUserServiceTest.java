package my.shop.web.admin.service.test;

import my.shop.domain.TbUser;

import my.shop.web.admin.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @author Guodong
 * @title: TbUserServiceTest
 * @projectName my-shop-module
 * @description: TODO
 * @date 2019/8/21 10:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-context-druid.xml", "classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest {
    @Autowired
    private TbUserService tbUserService;

    @Test
    public void testSelectAll(){
        List<TbUser> tbUsers = tbUserService.selectAll();
        for (TbUser tbUser : tbUsers){
            System.out.println(tbUser.getUsername());
        }
    }

    @Test
    public void  testSelectByUsername(){
        List<TbUser> tbUsers = tbUserService.selectByUsername("uni");
        for (TbUser tbUser : tbUsers){
            System.out.println(tbUser.getUsername());
        }
    }
    @Test
    public void testGetById(){
        TbUser tbUser = tbUserService.getById(39L);
        System.out.println(tbUser.getUsername());
    }

    @Test
    public void testInsert() {
        TbUser tbUser = new TbUser();
        tbUser.setEmail("admin@admin.com");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("admin".getBytes()));
        tbUser.setPhone("15888888888");
        tbUser.setUsername("HaHa");
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());

//        tbUserService.insert(tbUser);
    }

    @Test
    public void testMD5(){
        System.out.println(DigestUtils.md5DigestAsHex("admin".getBytes()));
    }

    @Test
    public void testDelete(){
        tbUserService.delete(38L);
    }
}
