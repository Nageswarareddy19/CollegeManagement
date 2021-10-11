package com.collegemanagement.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.collegemanagement.rest.entity.CollegeEntity;
import com.collegemanagement.rest.entity.DepartmentEntity;
import com.collegemanagement.rest.services.CollegeService;
import com.collegemanagement.rest.services.DepartmentService;

@RestController
@RequestMapping("/collegeService")
public class CollegeController {

	@Autowired
	private CollegeService collegeService;

	@Autowired
	private DepartmentService deptService;

	@PostMapping("/add-college")
	public String saveCollege(@RequestBody CollegeEntity college) {
		return collegeService.addCollege(college);
	}

	@GetMapping("/college-list")
	public List<CollegeEntity> getCollegeList() {
		return collegeService.getCollegeList();
	}

	@GetMapping("/college-by-id/{id}")
	public CollegeEntity collegeById(@PathVariable int id) {
		return collegeService.collegeById(id);
	}

	@PostMapping("/saveDept")
	public ResponseEntity<String> saveDeptDetails(@RequestBody CollegeEntity entity) {
		System.out.println(entity.toString());
		collegeService.addDept(entity);
		String status = "Department Details added successfully";

		return new ResponseEntity<String>(status, HttpStatus.CREATED);

	}

	@GetMapping("/depts/{collegeName}")
	public ResponseEntity<List<DepartmentEntity>> getDeptDetailsByCollgeName(
			@PathVariable("collegeName") String collegeName) {
		List<DepartmentEntity> listOfDepts = deptService.getDeptDetailsByCollegeName(collegeName);
		return new ResponseEntity<List<DepartmentEntity>>(listOfDepts, HttpStatus.OK);

	}

}
