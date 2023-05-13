package com.company.payroll.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.BankingInfo;

@Repository
public interface BankingInfoMapper {
    int deleteByPrimaryKey(Integer bId);

    int insert(BankingInfo row);

    int insertSelective(BankingInfo row);
    
    List<BankingInfo> selectList();

    BankingInfo selectByPrimaryKey(Integer bId);

    int updateByPrimaryKeySelective(BankingInfo row);

    int updateByPrimaryKey(BankingInfo row);
}