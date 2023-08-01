package com.stc.managingFiles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stc.managingFiles.entity.PermissionGroup;

@Repository
    public interface PermissionGroupRepository extends JpaRepository<PermissionGroup, Long> {
}
