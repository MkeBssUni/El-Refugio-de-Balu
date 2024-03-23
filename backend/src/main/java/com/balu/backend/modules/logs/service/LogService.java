package com.balu.backend.modules.logs.service;

import com.balu.backend.modules.logs.model.ILogRepository;
import com.balu.backend.modules.logs.model.Log;
import com.balu.backend.modules.logs.model.LogTypes;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@AllArgsConstructor
@Transactional
public class LogService {
    private final ILogRepository iLogRepository;

    @Transactional(rollbackFor = {SQLException.class,Exception.class})
    public boolean saveLog(String message, LogTypes logTypes, String tableAffected){
        try{
            Log log = new Log();
            log.saveLog(message,logTypes,tableAffected);
            iLogRepository.saveAndFlush(log);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
