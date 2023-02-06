package com.laiwen.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.laiwen.config.DataSourceTypeEnum;
import com.laiwen.config.SpecifyDataSource;
import com.laiwen.dao.AccountSet;
import com.laiwen.dao.mapper.AccountSetRepository;
import com.laiwen.secdao.PlatformActionLog;
import com.laiwen.secdao.mapper.PlatformActionLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author laiw
 * @date 2022/11/10
 */
@RestController
@RequestMapping("/api/dataSource/")
public class DataSourceController {

    @Autowired
    private AccountSetRepository accountSetRepository;
    @Autowired
    private PlatformActionLogRepository platformActionLogRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @GetMapping("primary")
    @ResponseBody
    public Object primary() {
        AccountSet accountSet = accountSetRepository.findByAccountNumber("19999220737");
        long count = accountSetRepository.count();
        Map<String, Object> map = new HashMap<>();
        map.put("accountSet", accountSet);
        map.put("count", count);
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    @GetMapping("secondary")
    @SpecifyDataSource(DataSourceTypeEnum.EXTERNAL_USER)
    @ResponseBody
    public Object secondary() {
        PlatformActionLog actionLog = platformActionLogRepository.findById("218733");
        long count = platformActionLogRepository.count();
        Map<String, Object> map = new HashMap<>();
        map.put("actionLog", actionLog);
        map.put("count", count);
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    @GetMapping("get")
    @ResponseBody
    public Object get() {
        AccountSet accountSet = accountSetRepository.findByAccountNumber("19999220737");
        PlatformActionLog actionLog = platformActionLogRepository.findById("218733");
        Map<String, Object> map = new HashMap<>();
        map.put("accountSet", accountSet);
        map.put("actionLog", actionLog);
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    @GetMapping("primary/query")
    @ResponseBody
    public Object primaryQuery() {
        List query = entityManager.createNativeQuery("SELECT count(1) FROM account_set").getResultList();
        Map<String, Object> map = new HashMap<>();
        map.put("count", CollectionUtils.isEmpty(query) ? 0 : Long.parseLong(query.get(0).toString()));
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

    @GetMapping("secondary/query")
    @SpecifyDataSource(DataSourceTypeEnum.EXTERNAL_USER)
    @ResponseBody
    public Object secondaryQuery() {
        List query = entityManager.createNativeQuery("SELECT count(1) FROM platform_action_log").getResultList();
        Map<String, Object> map = new HashMap<>();
        map.put("count", CollectionUtils.isEmpty(query) ? 0 : Long.parseLong(query.get(0).toString()));
        return JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

}
