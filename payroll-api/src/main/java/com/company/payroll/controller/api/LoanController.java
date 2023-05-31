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

import com.company.payroll.model.Loan;
import com.company.payroll.service.StaffApplicationService;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/loan")
public class LoanController {
	
	@Autowired
	private StaffApplicationService staffApplicationService;

	@Operation(summary="Get loan list")
	@GetMapping
	public ResponseEntity<PageInfo<Loan>> listLoan(@RequestParam(value="page", required=true) int page, @RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(staffApplicationService.listLoan(page, offset));
	}
	
	@Operation(summary="Get loan list by employee id")
	@GetMapping("/{id}/all")
	public ResponseEntity<Optional<List<Loan>>> listLoanByEId(@Parameter(description="employee id") @PathVariable("id") int eid) {
		return ResponseEntity.ok(staffApplicationService.findLoanByEId(eid));
	}
	
	@Operation(summary="Get loan info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Loan>> getById(@PathVariable("id") int lid) {
		return ResponseEntity.ok(staffApplicationService.findLoanById(lid));
	}
	
	@Operation(summary="Add loan info")
	@PostMapping
	public ResponseEntity<Loan> insert(@RequestBody Loan loan) {
		return ResponseEntity.ok(staffApplicationService.insertLoan(loan));
	}
	
	@Operation(summary="Update loan info.")
	@PutMapping("/{id}")
	public ResponseEntity<Loan> update(@RequestBody Loan loan) {
		return ResponseEntity.ok(staffApplicationService.updateLoan(loan));
	}
	
	@Operation(summary="Delete loan info.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") int lid) {
		Integer status = staffApplicationService.deleteLoan(lid);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
}
