package com.company.payroll.test.controller;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import com.company.payroll.controller.api.TrainingController;
import com.company.payroll.service.TrainingService;

@WebMvcTest(value=TrainingController.class, 
			excludeAutoConfiguration= {SecurityAutoConfiguration.class},
			excludeFilters=@ComponentScan.Filter(type=FilterType.REGEX, pattern="com.company.payroll.*.*Filter"))
public class TrainingControllerTest extends AbstractControllerTest {

	@InjectMocks
	private TrainingController trainingController;
	
	@MockBean
	private TrainingService trainingService;
	
	@Before
	@Override
	protected void setUp() {
		super.setUp();
	}

	@Override
	protected void createWhenSuccess() throws Exception {
		// TODO Auto-generated method stub
		super.createWhenSuccess();
	}

	@Override
	protected void createWhenFailed() throws Exception {
		// TODO Auto-generated method stub
		super.createWhenFailed();
	}

	@Override
	protected void queryAll() throws Exception {
		// TODO Auto-generated method stub
		super.queryAll();
	}

	@Override
	protected void queryById() throws Exception {
		// TODO Auto-generated method stub
		super.queryById();
	}
	
	@Test
	void queryListByEId() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	protected void updateWhenSuccess() throws Exception {
		// TODO Auto-generated method stub
		super.updateWhenSuccess();
	}

	@Override
	protected void updateWhenFailed() throws Exception {
		// TODO Auto-generated method stub
		super.updateWhenFailed();
	}

	@Override
	protected void deleteWhenSuccess() throws Exception {
		// TODO Auto-generated method stub
		super.deleteWhenSuccess();
	}

	@Override
	protected void deleteWhenFailed() throws Exception {
		// TODO Auto-generated method stub
		super.deleteWhenFailed();
	}

}
