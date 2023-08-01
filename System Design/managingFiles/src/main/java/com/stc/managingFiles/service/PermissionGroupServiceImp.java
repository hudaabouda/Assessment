package com.stc.managingFiles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stc.managingFiles.entity.PermissionGroup;
import com.stc.managingFiles.repository.PermissionGroupRepository;


@Service
public class PermissionGroupServiceImp implements PermissionGroupService {

    @Autowired
    PermissionGroupRepository groupRepository;

    @Override
    public PermissionGroup createPermissionGroup(PermissionGroup permissionGroup) {
        return groupRepository.save(permissionGroup);
    }
}
