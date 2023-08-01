package com.stc.managingFiles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stc.managingFiles.entity.Permission;
import com.stc.managingFiles.service.PermissionService;

@RestController
@RequestMapping("/user")
public class UserPermissionController {
	@Autowired
	PermissionService permissionService;

	@PostMapping(path = "/permission/{groupId}")
	public ResponseEntity<Permission> CreatePermission(@RequestBody Permission permission, @PathVariable long groupId) {
		Permission savedPermission = permissionService.createPermission(permission, groupId);

		if (savedPermission != null)
			return ResponseEntity.status(HttpStatus.CREATED).body(savedPermission);
		else
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
}