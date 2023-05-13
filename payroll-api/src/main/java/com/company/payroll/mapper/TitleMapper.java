package com.company.payroll.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.company.payroll.model.Title;

@Repository
public interface TitleMapper {
    int deleteByPrimaryKey(Integer titleno);

    int insert(Title row);

    int insertSelective(Title row);
    
    List<Title> selectList();

    Title selectByPrimaryKey(Integer titleno);

    int updateByPrimaryKeySelective(Title row);

    int updateByPrimaryKey(Title row);
}