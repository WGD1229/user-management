package my.shop.web.admin.service.impl;

import my.shop.commons.dto.BaseResult;
import my.shop.commons.dto.PageInfo;
import my.shop.commons.utils.RegexpUtils;
import my.shop.commons.validator.BeanValidator;
import my.shop.domain.TbUser;
import my.shop.web.admin.dao.TbUserDao;
import my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Guodong
 * @title: TbUserServiceImpl
 * @projectName my-shop-module
 * @description: TODO
 * @date 2019/8/20 17:12
 */
@Service
public class TbUserServiceImpl implements TbUserService {
  @Autowired private TbUserDao tbUserDao;

  @Override
  public List<TbUser> selectAll() {
    return tbUserDao.selectAll();
  }

  @Override
  public BaseResult save(TbUser tbUser) {
    String validator = BeanValidator.validator(tbUser);
    // 验证不通过
    if (validator != null) {
      return BaseResult.fail(validator);
    }

    // 通过验证
    else {
      tbUser.setUpdated(new Date());

      // 新增用户
      if (tbUser.getId() == null) {
        // 密码需要加密处理
        tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
        tbUser.setCreated(new Date());
        tbUserDao.insert(tbUser);
      }

      // 编辑用户
      else {
        // 编辑用户时如果没有输入密码则沿用原来的密码
        if (StringUtils.isBlank(tbUser.getPassword())) {
          TbUser oldTbUser = getById(tbUser.getId());
          tbUser.setPassword(oldTbUser.getPassword());
        } else {
          // 验证密码是否符合规范，密码长度介于 6 - 20 位之间
          if (StringUtils.length(tbUser.getPassword()) < 6 || StringUtils.length(tbUser.getPassword()) > 20) {
            return BaseResult.fail("密码长度必须介于 6 - 20 位之间");
          }

          // 设置密码加密
          tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
        }
        update(tbUser);
      }

      return BaseResult.success("保存用户信息成功");
    }
  }

  @Override
  public void delete(Long id) {
    tbUserDao.delete(id);
  }

  @Override
  public TbUser getById(Long id) {
    return tbUserDao.getById(id);
  }

  @Override
  public void update(TbUser tbUser) {
    tbUserDao.update(tbUser);
  }

  @Override
  public List<TbUser> selectByUsername(String username) {
    return tbUserDao.selectByUsername(username);
  }

  @Override
  public TbUser login(String email, String password) {
    TbUser tbUser = tbUserDao.getByEmail(email);
    if (tbUser != null) {
      String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
      if (md5Password.equals(tbUser.getPassword())) {
        return tbUser;
      }
    }
    return null;
  }

  @Override
  public List<TbUser> search(TbUser tbUser) {
    return tbUserDao.search(tbUser);
  }

  @Override
  public void deleteMulti(String[] ids) {
    tbUserDao.deleteMulti(ids);
  }

  /**
   * 用户信息验证
   *
   * @param tbUser
   */
  private BaseResult checkTbUser(TbUser tbUser) {

    BaseResult baseResult = BaseResult.success();
    if (StringUtils.isBlank(tbUser.getEmail())) {
      baseResult = BaseResult.fail("邮箱不能为空，请重新输入");
    }
    else if (!RegexpUtils.checkEmail(tbUser.getEmail())) {
        baseResult = BaseResult.fail("邮箱格式错误，请重新输入");
    }

    else if (StringUtils.isBlank(tbUser.getPassword())) {
        baseResult = BaseResult.fail("密码不能为空，请重新输入");
    }
    else if (StringUtils.isBlank(tbUser.getUsername())) {
        baseResult = BaseResult.fail("姓名不能为空，请重新输入");
    }
    else if (StringUtils.isBlank(tbUser.getPhone())) {
        baseResult = BaseResult.fail("手机号不能为空，请重新输入");
    }
    else if (!RegexpUtils.checkPhone(tbUser.getPhone())) {
        baseResult = BaseResult.fail("手机号格式错误，请重新输入");
    }


      return baseResult;
  }

  @Override
  public PageInfo<TbUser> page(int start, int length, int draw, TbUser tbUser) {
    int count = count(tbUser);

    Map<String, Object> params = new HashMap<>();
    params.put("start", start);
    params.put("length", length);
    params.put("pageParams", tbUser);

    PageInfo<TbUser> pageInfo = new PageInfo<>();
    pageInfo.setDraw(draw);
    pageInfo.setRecordsTotal(count);
    pageInfo.setRecordsFiltered(count);
    pageInfo.setData(tbUserDao.page(params));

    return pageInfo;
  }

  /**
   * 查询总笔数
   *
   * @return
   */
  @Override
  public int count(TbUser tbUser) {
    return tbUserDao.count(tbUser);
  }

}
