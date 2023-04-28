package com.company.payroll.controller.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.payroll.model.Manager;
import com.company.payroll.service.ManagerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/manager")
public class ManagerController {
	private static final String VALUE_ONE = "{\"fullname\": \"string\", \"gender\": \"string\", "
										  + "\"age\": 0, \"martialstatus\": \"string\", \"education\": \"string\", "
										  + "\"address\": \"string\", \"state\": \"string\", \"country\": \"string\", \"phone\": \"string\", "
										  + "\"datehired\": \"2023-04-28\", \"attachment\": \"string\", \"imgUser\": \"string\", \"deptno\": 0, "
										  + "\"titleno\": 0, \"bid\": 0, \"sid\": 0, \"date_of_birth\": \"2023-04-28\", "
										  + "\"company_email\": \"string\"}";
	
	private ManagerService managerService;
	
	public ManagerController(ManagerService managerService) {
		this.managerService = managerService;
	}
	
	@Operation(summary="Get manager list")
	@GetMapping("/list")
	public ResponseEntity<List<Manager>> listManager() {
		return ResponseEntity.ok(managerService.getList());
	}
	
	@Operation(summary="Get manager info by id")
	@GetMapping("/list/information/{id}")
	public ResponseEntity<Manager> getById(@Parameter() @PathVariable("id") int mid) {
		return ResponseEntity.ok(managerService.getById(mid));
	}
	
	@Operation(summary="Insert manager info",
			   responses= {@ApiResponse(responseCode="200",
					   					description="Value return 1 for insert success.",
					   					content=@Content(examples= {@ExampleObject(value="1")})),
					   	   @ApiResponse(responseCode="403",
					   			   		description="Value return 0 for insert fail.",
					   			   		content=@Content(examples= {@ExampleObject(value="0")}))})
	@io.swagger.v3.oas.annotations.parameters.RequestBody(
		   	 content= {@Content(mediaType="application/json", 
			 schema= @Schema(implementation = Manager.class),
			 examples= {@ExampleObject(name="Example 1", value=VALUE_ONE)})})
	@PostMapping("/insert")
	public ResponseEntity<Integer> insert(@RequestBody Manager manager) {
		Integer status = managerService.insert(manager);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
	
	@Operation(summary="Update manager info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for update success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for update fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@PutMapping("/list/information/{id}/update")
	public ResponseEntity<Integer> update(@RequestBody Manager manager) {
		Integer status = managerService.update(manager);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}

	@Operation(summary="Delete manager info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for delete success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for delete fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@DeleteMapping("/list/information/{id}/delete")
	public ResponseEntity<Integer> delete(@Parameter() @PathVariable("id") int id) {
		Integer status = managerService.delete(id);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
//	@GetMapping("/list/count/all")
//	public Integer countManager() {
//		return managerService.countManager();
//	}

//	@GetMapping("/list/count/active")
//	public Integer countAvailableManager() {
//		return managerService.countAvailableManager();
//	}
}
