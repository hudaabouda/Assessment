package com.stc.managingFiles.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.stc.managingFiles.entity.FileData;
import com.stc.managingFiles.entity.Item;
import com.stc.managingFiles.entity.Permission;
import com.stc.managingFiles.entity.PermissionGroup;
import com.stc.managingFiles.enums.ItemType;
import com.stc.managingFiles.repository.FileRepository;
import com.stc.managingFiles.repository.ItemRepository;
import com.stc.managingFiles.repository.PermissionGroupRepository;
import com.stc.managingFiles.repository.PermissionRepository;
import com.stc.managingFiles.exception.ResourceNotFoundException;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;


@Service
public class ItemServiceImp implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    PermissionGroupRepository groupRepository;
    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public Item createItem(Item item, ItemType itemType) throws IOException {
        switch (itemType) {
            case FOLDER : {
                item.setParentItem(getParentSpace(item.getParentItem()));
                item.setPermissionGroup(item.getParentItem().getPermissionGroup());

            }
            case FILE :{
                item.setParentItem(getParentSpace(item.getParentItem()));
                item.setPermissionGroup(item.getParentItem().getPermissionGroup());
                return saveBinaryFile(item);
            }
            default: createSpace(item);

        }

        return itemRepository.save(item);
    }

    private void createSpace(Item item) {
        if (item.getPermissionGroup() != null && item.getPermissionGroup().getId() != null) {
            Optional<PermissionGroup> group = groupRepository.findById(item.getPermissionGroup().getId());
            if (group.isPresent()) {
                group.get().setPermissions(applyUserPermissions(item.getPermissionGroup()));
                item.setPermissionGroup(group.get());
            } else throw new ResourceNotFoundException("Group", "Id", item.getPermissionGroup().getId());
        }
    }

    private Item getParentSpace(Item parentItem) {
        if (parentItem != null && parentItem.getId() != null) {
            Optional<Item> parent = itemRepository.findById(parentItem.getId());
            if (parent.isPresent()) {
                return parent.get();
            } else throw new ResourceNotFoundException("Space", "Id", parentItem.getId());
        }
        return parentItem;
    }

    private List<Permission> applyUserPermissions(PermissionGroup permissionGroup) {
        List<Permission> userPermissions = new ArrayList<>();
        if (permissionGroup != null && permissionGroup.getPermissions() != null) {
            permissionGroup.getPermissions().forEach(user -> {
                userPermissions.add(updateUserPermission(user));
            });
            return userPermissions;
        }
        return userPermissions;
    }

    private Permission updateUserPermission(Permission user) {
        if (user.getId() != null) {
            Optional<Permission> permission = permissionRepository.findById(user.getId());
            if (permission.isPresent()) {
                permission.get().setPermissionLevel(user.getPermissionLevel());
                permissionRepository.save(permission.get());
                return permission.get();
            } else throw new ResourceNotFoundException("User", "Id", user.getId());
        }
        return user;
    }

    private Item saveBinaryFile(Item item) throws IOException {
        Item savedItem = itemRepository.save(item);
        if (savedItem != null) {
            Resource resource = resourceLoader.getResource("classpath:" + "Documents" + File.separator + item.getName());
            File file = resource.getFile();
            byte[] fileBytes = Files.readAllBytes(file.toPath());
            FileData fileData = new FileData();
            fileData.setBinary(fileBytes);
            fileData.setItem(item);
            fileRepository.save(fileData);
        }
        return savedItem;
    }
}
