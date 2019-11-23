package com.tu.poscard.data.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @auther Tu
 * @date 2018/12/20
 * @email enum@foxmail.com
 */
public class BaseModel implements Serializable {
    private static final long serialVersionUID = 7774237775214971569L;
    private Integer id;
    private Date createTime;
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
