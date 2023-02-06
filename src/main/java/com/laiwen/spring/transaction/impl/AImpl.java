package com.laiwen.spring.transaction.impl;

import com.laiwen.secdao.PlatformActionLog;
import com.laiwen.secdao.mapper.PlatformActionLogRepository;
import com.laiwen.spring.transaction.A;
import com.laiwen.spring.transaction.B;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author laiw
 * @date 2023/1/31 09:44
 */
@Service
public class AImpl implements A {

    @Autowired
    private PlatformActionLogRepository platformActionLogRepository;
    @Autowired
    private B b;

    //@Transactional(rollbackOn = Exception.class)
    @Override
    public void aVoid() {
        PlatformActionLog actionLog = platformActionLogRepository.findById("33");
        actionLog.setQueryParam("a");
        platformActionLogRepository.save(actionLog);

        b.bVoid();

        int i = 1/0;

    }

    @Transactional(rollbackOn = Exception.class)
    public void abVoid() {
        PlatformActionLog actionLog = platformActionLogRepository.findById("33");
        actionLog.setQueryParam("ab");
        platformActionLogRepository.save(actionLog);
        int i = 1/0;
    }

}
