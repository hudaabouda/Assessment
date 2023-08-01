package com.stc.managingFiles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stc.managingFiles.entity.PermissionGroup;
import com.stc.managingFiles.service.PermissionGroupService;




@RestController
@RequestMapping("/group")
public class PermissionGroupController {
	@Autowired
	PermissionGroupService permissionGroupService;

	@PostMapping(path = "/permissionGroup")
	public ResponseEntity<PermissionGroup> CreatePermissionGroup(@RequestBody PermissionGroup permissionGroup) {
		PermissionGroup savedPermissionGroup = permissionGroupService.createPermissionGroup(permissionGroup);

		if (savedPermissionGroup != null)
			return ResponseEntity.status(HttpStatus.CREATED).body(savedPermissionGroup);
		else
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

}