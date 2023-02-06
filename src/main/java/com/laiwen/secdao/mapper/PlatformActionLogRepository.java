package com.laiwen.secdao.mapper;

import com.laiwen.secdao.PlatformActionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PlatformActionLogRepository extends JpaRepository<PlatformActionLog, Long>,JpaSpecificationExecutor<PlatformActionLog>{

    @Query(value="select pal.* from platform_action_log pal where pal.id = ?1 ", nativeQuery=true)
    PlatformActionLog findById(String id);

}