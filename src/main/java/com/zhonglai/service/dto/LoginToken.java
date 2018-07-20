package com.zhonglai.service.dto;

/**
 * 用户登陆token对象
 * Created by zhonglai on 2016/12/2.
 */
public class LoginToken {
    private int type; //类型 1用户端，2管理员端，3技术端
    private String id; //主键
    private int time; //时间
    private String parentJson; //父类对象json串

    public LoginToken()
    {

    }

    /**
     * 实例化对象
     * @param type 类型(1用户端，2管理员端，3技术端)
     * @param id 主键
     * @param time 时间
     */
    public LoginToken(int type,String id,int time)
    {
        this.type = type;
        this.id = id;
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getParentJson() {
        return parentJson;
    }

    public void setParentJson(String parentJson) {
        this.parentJson = parentJson;
    }
}
