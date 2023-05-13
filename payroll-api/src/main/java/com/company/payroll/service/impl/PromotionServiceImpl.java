package com.company.payroll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.payroll.mapper.PromotionMapper;
import com.company.payroll.model.Promotion;
import com.company.payroll.service.PromotionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class PromotionServiceImpl implements PromotionService {

	@Autowired
	private PromotionMapper promotionMapper;

	@Override
	public PageInfo<Promotion> getListByPage(int page, int offset) {
		PageHelper.startPage(page, offset);
		List<Promotion> list = promotionMapper.selectList();
		return new PageInfo<Promotion>(list);
	}

	@Override
	public Promotion getById(int pid) {
		return promotionMapper.selectByPrimaryKey(pid);
	}

	@Override
	public Integer insert(Promotion promotion) {
		return promotionMapper.insertSelective(promotion);
	}

	@Override
	public Integer update(Promotion promotion) {
		return promotionMapper.updateByPrimaryKeySelective(promotion);
	}

	@Override
	public Integer delete(int pid) {
		return promotionMapper.deleteByPrimaryKey(pid);
	}
}
