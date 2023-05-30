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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/loan")
public class LoanController {
	private static final String VALUE_ONE = "{\"reason\": \"string\", \"mid\": 0, \"eid\": 0, "
										  + "\"reference_number\": \"string\", \"amount\": 0, \"application_date\": \"2023-04-28\", "
										  + "\"status\": \"string\", \"approve_date\": \"2023-04-28\"}";
	
	@Autowired
	private StaffApplicationService staffApplicationService;

	@Operation(summary="Get loan list")
	@GetMapping
	public ResponseEntity<PageInfo<Loan>> listLoan(@RequestParam(value="page", required=true) int page, 
			  									@RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(staffApplicationService.listLoan(page, offset));
	}
	
	@Operation(summary="Get loan list by employee id")
	@GetMapping("/{id}/all")
	public ResponseEntity<Optional<List<Loan>>> listLoanByEId(@Parameter(description="employee id") @PathVariable("id") int eid) {
		return ResponseEntity.ok(staffApplicationService.findLoanByEId(eid));
	}
	
	@Operation(summary="Get loan info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Loan>> getById(@Parameter() @PathVariable("id") int lid) {
		return ResponseEntity.ok(staffApplicationService.findLoanById(lid));
	}
	
	@Operation(summary="Add loan info",
			   responses= {@ApiResponse(responseCode="200",
					   					description="Value return 1 for insert success.",
					   					content=@Content(examples= {@ExampleObject(value="1")})),
					   	   @ApiResponse(responseCode="403",
					   			   		description="Value return 0 for insert fail.",
					   			   		content=@Content(examples= {@ExampleObject(value="0")}))})
	@io.swagger.v3.oas.annotations.parameters.RequestBody(
			   	 content= {@Content(mediaType="application/json", 
	   			 schema= @Schema(implementation = Loan.class),
	   			 examples= {@ExampleObject(name="Example 1", value=VALUE_ONE)})})
	@PostMapping
	public ResponseEntity<Loan> insert(@RequestBody Loan loan) {
		return ResponseEntity.ok(staffApplicationService.insertLoan(loan));
	}
	
	@Operation(summary="Update loan info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for update success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for update fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@PutMapping("/{id}")
	public ResponseEntity<Loan> update(@RequestBody Loan loan) {
		return ResponseEntity.ok(staffApplicationService.updateLoan(loan));
	}
	
	@Operation(summary="Delete loan info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for delete success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for delete fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@Parameter() @PathVariable("id") int lid) {
		Integer status = staffApplicationService.deleteLoan(lid);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
}
