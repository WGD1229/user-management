package my.shop.web.admin.service;

import my.shop.commons.dto.BaseResult;
import my.shop.commons.dto.PageInfo;
import my.shop.domain.TbUser;

import java.util.List;

/**
 * @author Guodong
 * @title: TbUserService
 * @projectName my-shop-module
 * @description: TODO
 * @date 2019/8/20 16:22
 */
public interface TbUserService {
        /**
         * 查询全部用户信息
         * @return
         */
         List<TbUser> selectAll();

        /**
         * 添加用户信息
         * @param tbUser
         */
        BaseResult save(TbUser tbUser);

        /**
         * 根据id删除用户
         * @param id
         */
         void delete (Long id);

         TbUser getById(Long id);

         void update(TbUser tbUser);

         List<TbUser> selectByUsername(String username);

         TbUser login(String email, String password);

         List<TbUser> search(TbUser tbUser);

         void deleteMulti(String[] ids);

        /**
         * 分页查询
         * @param start
         * @param length
         * @return
         */
        PageInfo<TbUser> page(int start, int length, int draw, TbUser tbUser);

        /**
         * 查询总笔数
         * @return
         */
        int count(TbUser tbUser);

}