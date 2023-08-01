package com.stc.managingFiles.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.stc.managingFiles.enums.ItemType;

@Entity
@Table(name = "item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private ItemType type;

	private String name;

	@ManyToOne(targetEntity = PermissionGroup.class)
	@JoinColumn(name = "permission_group_id")
	private PermissionGroup permissionGroup;

	@ManyToOne(targetEntity = Item.class)
	@JoinColumn(name = "parent_item_id")
	private Item parentItem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ItemType getType() {
		return type;
	}

	public void setType(ItemType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PermissionGroup getPermissionGroup() {
		return permissionGroup;
	}

	public void setPermissionGroup(PermissionGroup permissionGroup) {
		this.permissionGroup = permissionGroup;
	}

	public Item getParentItem() {
		return parentItem;
	}

	public void setParentItem(Item parentItem) {
		this.parentItem = parentItem;
	}

}