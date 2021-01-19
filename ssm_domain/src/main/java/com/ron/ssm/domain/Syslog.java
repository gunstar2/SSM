package com.ron.ssm.domain;

import com.ron.ssm.utills.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//AOP日志
public class Syslog {
    private String id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date visitTime;//访问时常
    private String visitTimeStr;
    private String username; //访问人
    private String ip;
    private String url;
    private Long executionTime;//访问时常
    private String method;//访问方法

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisitTimeStr() {
        if (visitTime!=null){
            visitTimeStr= DateUtils.DateToString(visitTime,"yyyy-MM-dd HH:mm:ss");
        }
        return visitTimeStr;
    }

    public void setVisitTimeStr(String visitTimeStr) {

        this.visitTimeStr = visitTimeStr;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "Syslog{" +
                "id='" + id + '\'' +
                ", visitTime=" + visitTime +
                ", visitTimeStr='" + visitTimeStr + '\'' +
                ", username='" + username + '\'' +
                ", ip='" + ip + '\'' +
                ", url='" + url + '\'' +
                ", executionTime=" + executionTime +
                ", method='" + method + '\'' +
                '}';
    }
}
