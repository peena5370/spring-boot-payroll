package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.TrainingMapper;
import com.company.payroll.model.Training;
import com.company.payroll.service.TrainingService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class TrainingServiceImpl implements TrainingService {

	@Autowired
	private TrainingMapper trainingMapper;

	@Override
	public PageInfo<Training> getListByPage(int page, int offset) {
		PageHelper.startPage(page, offset);
		List<Training> list = trainingMapper.selectList();
		return new PageInfo<Training>(list);
	}

	@Override
	public Training getById(int tId) {
		return trainingMapper.selectByPrimaryKey(tId);
	}

	@Override
	public List<Training> getListByEId(int eId) {
		return trainingMapper.selectListByEId(eId);
	}

	@Override
	public Integer insert(Training training) {
		return trainingMapper.insertSelective(training);
	}

	@Override
	public Integer update(Training training) {
		return trainingMapper.updateByPrimaryKeySelective(training);
	}

	@Override
	public Integer delete(int tId) {
		return trainingMapper.deleteByPrimaryKey(tId);
	}
}