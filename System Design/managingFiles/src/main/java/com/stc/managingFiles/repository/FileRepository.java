package com.stc.managingFiles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stc.managingFiles.entity.FileData;

@Repository
public interface FileRepository extends JpaRepository<FileData, Long> {
}
