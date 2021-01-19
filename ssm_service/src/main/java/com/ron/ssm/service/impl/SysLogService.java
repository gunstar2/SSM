package com.ron.ssm.service.impl;

import com.ron.ssm.dao.ISysLogDao;
import com.ron.ssm.domain.Syslog;
import com.ron.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class SysLogService implements ISysLogService {
    @Autowired
    private ISysLogDao sysLogDao;
    @Override
    public void save(Syslog syslog) throws Exception {
        syslog.setId(UUID.randomUUID().toString().replace("-",""));
        System.out.println(syslog);
        sysLogDao.save(syslog);

    }

    @Override
    public List<Syslog> findAll() throws Exception {
        return sysLogDao.findAll();
    }
}
