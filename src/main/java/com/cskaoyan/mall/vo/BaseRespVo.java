package com.cskaoyan.mall.vo;

/**
 * 结果json
 *
 * @param <T>
 * @author hadymic
 */
public class BaseRespVo<T> {
    private int errno;
    private String errmsg;
    private T data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> BaseRespVo success(T data) {
        BaseRespVo<T> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrmsg("成功");
        baseRespVo.setData(data);
        return baseRespVo;
    }

    public static BaseRespVo fail(String errmsg) {
        BaseRespVo baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrmsg(errmsg);
        baseRespVo.setErrno(500);
        return baseRespVo;
    }
}
