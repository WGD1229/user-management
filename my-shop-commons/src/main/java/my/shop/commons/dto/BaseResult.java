package my.shop.commons.dto;

import java.io.Serializable;

/**
 * 自定义状态码
 * @author Guodong
 * @title: BaseResult
 * @projectName my-shop-module
 * @description: TODO
 * @date 2019/8/27 21:47
 */
public class BaseResult implements Serializable {
    private Integer status;
    private String message;
    public static final Integer STATUS_SUCCESS = 200;
    public static final Integer STATUS_FAIL = 500;

    private static BaseResult createBaseResult(Integer status, String message){
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(status);
        baseResult.setMessage(message);
        return baseResult;
    }


    public static BaseResult success(){
        return BaseResult.createBaseResult(STATUS_SUCCESS,"成功");
    }
    public static BaseResult success(String message){
        return BaseResult.createBaseResult(STATUS_SUCCESS,message);
    }
    public static BaseResult success(Integer status, String message){
        return BaseResult.createBaseResult(status,message);
    }
    public static BaseResult fail(){
        return BaseResult.createBaseResult(STATUS_FAIL,"失败");
    }
    public static BaseResult fail(String message){
        return BaseResult.createBaseResult(STATUS_FAIL,message);

    }
    public static BaseResult fail(Integer status, String message){
        return BaseResult.createBaseResult(status,message);
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
