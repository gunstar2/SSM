package com.ron.ssm.service;

import com.ron.ssm.domain.Syslog;

import java.util.List;

public interface ISysLogService {

    public void save(Syslog syslog) throws Exception;

    List<Syslog> findAll()throws Exception;
}
