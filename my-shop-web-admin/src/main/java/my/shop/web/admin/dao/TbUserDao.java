package my.shop.web.admin.dao;

import my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Guodong
 * @title: TbUserDao
 * @projectName my-shop-module
 * @description: TODO
 * @date 2019/8/20 16:20
 */
@Repository
public interface TbUserDao {
    /**
     * 查询
     * @return
     */
    List<TbUser> selectAll();

    /**
     * 添加
     * @param tbUser
     */
    void insert (TbUser tbUser);

    /**
     * 根据id删除用户信息
     * @param id
     */
    void delete (Long id);

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    TbUser getById(Long id);

    void update(TbUser tbUser);

    List<TbUser> selectByUsername(String username);

    TbUser getByEmail(String email);

    List<TbUser> search(TbUser tbUser);

    void deleteMulti(String[] ids);

    /**
     * 分页查询
     *
     * @param params，需要两个参数，start/记录开始的位置 length/每页记录数
     * @return
     */
    List<TbUser> page(Map<String, Object> params);

    /**
     * 查询总笔数
     *
     * @return
     */
    int count(TbUser tbUser);
}
