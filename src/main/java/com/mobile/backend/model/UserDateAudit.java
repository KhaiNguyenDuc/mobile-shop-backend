package com.mobile.backend.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class UserDateAudit implements Serializable{

	private static final long serialVersionUID = 1L;
	@CreatedBy
	@Column(name = "create_by", updatable = false)
	private Long createBy;
	
	@LastModifiedBy
	@Column(name = "update_by")
	private Long updateBy;
	
	@CreatedDate
	@Column(name = "createAt", nullable = false, updatable = false)
	private Date createAt;

	@LastModifiedDate
	@Column(name = "updateAt", nullable = false)
	private Date updatedAt;
}
