package com.stc.managingFiles.service;

import com.stc.managingFiles.entity.Permission;

public interface PermissionService {
    Permission createPermission(Permission permission, long groupId);
}
