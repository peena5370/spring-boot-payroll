package com.company.payroll.controller.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.payroll.model.StaffLoan;
import com.company.payroll.service.StaffApplicationService;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/application/loan")
public class StaffLoanController {
	
	@Autowired
	private StaffApplicationService staffApplicationService;

	@Operation(summary="Get loan list")
	@GetMapping
	public ResponseEntity<PageInfo<StaffLoan>> list(@RequestParam(value="page", required=true) int page, @RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(staffApplicationService.listLoan(page, offset));
	}
	
	@Operation(summary="Get loan list by employee id")
	@GetMapping("/{id}/all")
	public ResponseEntity<Optional<List<StaffLoan>>> listLoanByStaffId(@Parameter(description="employee id") @PathVariable("id") Integer staffId) {
		return ResponseEntity.ok(staffApplicationService.findLoanByStaffId(staffId));
	}
	
	@Operation(summary="Get loan info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<StaffLoan>> findById(@PathVariable("id") Integer loanId) {
		return ResponseEntity.ok(staffApplicationService.findLoanById(loanId));
	}
	
	@Operation(summary="Add staffLoan info")
	@PostMapping
	public ResponseEntity<StaffLoan> insert(@RequestBody StaffLoan staffLoan) {
		return ResponseEntity.ok(staffApplicationService.insertLoan(staffLoan));
	}
	
	@Operation(summary="Update staffLoan info.")
	@PutMapping("/{id}")
	public ResponseEntity<StaffLoan> update(@RequestBody StaffLoan staffLoan) {
		return ResponseEntity.ok(staffApplicationService.updateLoan(staffLoan));
	}
	
	@Operation(summary="Delete loan info.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") Integer loanId) {
		return ResponseEntity.ok(staffApplicationService.deleteLoan(loanId));
	}
}