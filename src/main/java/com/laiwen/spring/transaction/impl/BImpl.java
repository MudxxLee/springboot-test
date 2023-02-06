package com.laiwen.spring.transaction.impl;

import com.laiwen.secdao.PlatformActionLog;
import com.laiwen.secdao.mapper.PlatformActionLogRepository;
import com.laiwen.spring.transaction.B;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author laiw
 * @date 2023/1/31 09:44
 */
@Service
public class BImpl implements B {

    @Autowired
    private PlatformActionLogRepository platformActionLogRepository;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void bVoid() {
        PlatformActionLog actionLog = platformActionLogRepository.findById("33");
        actionLog.setQueryParam("b");
        platformActionLogRepository.save(actionLog);
    }

}
