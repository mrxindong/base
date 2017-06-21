package com.sx_report.dao.mapper;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.sx_report.dao.annotation.MyBatisDao;
import com.sx_report.model.SEC_USER;

@MyBatisDao
@Component
public interface SEC_USERMapper {

    int insert(SEC_USER record);

    int insertSelective(SEC_USER record);
    
    List<SEC_USER> find();
    
    Map isSummeryDepart(@Param("sid") BigDecimal sid);
    
    List<Map> findPlanStateIds(@Param("sid") BigDecimal sid ,@Param("parentProId") BigDecimal parentProId,@Param("departlevel") Long departlevel);
    
    List<Map> getChildDepartInfo(@Param("sid")BigDecimal sid ,@Param("parentProId")BigDecimal parentProId);
    
    List<Map> getDepartInfo(@Param("sid")BigDecimal sid ,@Param("parentProId")BigDecimal parentProId);
    
    List<Map> getDepartInfoOld(@Param("sid")BigDecimal sid ,@Param("parentProId")BigDecimal parentProId);
    
    List<Map> findSummaryStateIds(@Param("sid") BigDecimal sid ,@Param("parentProId") BigDecimal parentProId);
    
    List<Map> findChildStore(@Param("sid")BigDecimal sid ,@Param("parentProId")BigDecimal parentProId,@Param("departlevel")Long departlevel);
    
    List<Map> findMajorStore(@Param("sid")BigDecimal sid ,@Param("parentProId")BigDecimal parentProId,@Param("departlevel")Long departlevel);
    
    List<Map> getDefineInfo(@Param("columns")String columns ,@Param("tableName")String tableName,@Param("condation")String condation);
    
}