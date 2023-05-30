package com.company.payroll.controller.api;

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
import org.springframework.web.multipart.MultipartFile;

import com.company.payroll.model.Resignation;
import com.company.payroll.service.StaffMiscellaneousService;
import com.company.payroll.util.FileUtils;
import com.github.pagehelper.PageInfo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/resign")
public class ResignationController {
	private static final String VALUE_ONE = "{\"reason\": \"string\", \"resigndate\": \"2023-04-28T13:46:24.820Z\", "
										  + "\"resignstatus\": \"string\", \"attachment\": \"string\"}";
	
	@Autowired
	private StaffMiscellaneousService staffMiscellaneousService;
	
	@Autowired
    private FileUtils fileUtils;

	@Operation(summary="Get resignation list")
	@GetMapping
	public ResponseEntity<PageInfo<Resignation>> listResignation(@RequestParam(value="page", required=true) int page, 
			  												@RequestParam(value="size", required=true) int offset) {
		return ResponseEntity.ok(staffMiscellaneousService.listResignation(page, offset));
	}
	
	@Operation(summary="Get resign info by id")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Resignation>> getById(@Parameter() @PathVariable("id") int id) {
		return ResponseEntity.ok(staffMiscellaneousService.findResignationById(id));
	}
	
	@Operation(summary="Insert resign info",
			   responses= {@ApiResponse(responseCode="200",
					   					description="Value return 1 for insert success.",
					   					content=@Content(examples= {@ExampleObject(value="1")})),
					   	   @ApiResponse(responseCode="403",
					   			   		description="Value return 0 for insert fail.",
					   			   		content=@Content(examples= {@ExampleObject(value="0")}))})
	@io.swagger.v3.oas.annotations.parameters.RequestBody(
			   	 content= {@Content(mediaType="application/json", 
	   			 schema= @Schema(implementation = Resignation.class),
	   			 examples= {@ExampleObject(name="Example 1", value=VALUE_ONE)})})
	@PostMapping
	public ResponseEntity<Resignation> insert(@RequestBody Resignation resignation) {		
		return ResponseEntity.ok(staffMiscellaneousService.insertResignation(resignation));
	}
	
	/**
	 * @todo to be implement and test
	 * @param file
	 * @param resignation
	 * @return
	 */
	public ResponseEntity<Resignation> insertWithFile(@RequestParam("file") MultipartFile file, @RequestBody Resignation resignation) {
		String filepath = "/resign_files";
		String uploadPath = fileUtils.fileUpload(file, filepath);
		
		return ResponseEntity.ok(staffMiscellaneousService.insertResignation(resignation));
	}
	
	@Operation(summary="Update resign info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for update success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for update fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@PutMapping("/{id}")
	public ResponseEntity<Resignation> update(@RequestBody Resignation resignation) {	
		return ResponseEntity.ok(staffMiscellaneousService.updateResignation(resignation));
	}
	
	@Operation(summary="Delete resign info.",
			   responses= {@ApiResponse(responseCode="200",
										description="Value return 1 for delete success.",
										content=@Content(examples= {@ExampleObject(value="1")})),
					   	  @ApiResponse(responseCode="403",
					   	  				description="Value return 0 for delete fail.",
					   	  				content=@Content(examples= {@ExampleObject(value="0")}))})
	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> delete(@Parameter @PathVariable("id") int id) {
		Integer status = staffMiscellaneousService.deleteResignation(id);
		if(status==0) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(status);
		}
		
		return ResponseEntity.ok(status);
	}
}
