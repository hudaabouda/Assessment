package com.stc.managingFiles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stc.managingFiles.entity.Permission;
import com.stc.managingFiles.repository.PermissionGroupRepository;
import com.stc.managingFiles.repository.PermissionRepository;



@Service
public class PermissionServiceImp implements PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    PermissionGroupRepository groupRepository;
    @Override
    public Permission createPermission(Permission permission, long groupId) {
        return permissionRepository.save(permission);
    }
}
