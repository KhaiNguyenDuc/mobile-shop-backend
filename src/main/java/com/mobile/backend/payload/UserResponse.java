package com.mobile.backend.payload;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mobile.backend.model.user.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
	private Long id;
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private Boolean enabled;
	private Date birthday;
	private String address;
	private String image;

	private List<Role> roles;

	public Boolean isEnabled() {
		return this.enabled;
	}

	public List<Role> getRoles() {
		return roles == null ? null : new ArrayList<>(this.roles);
	}

	public void setRoles(List<Role> roles) {
		if (roles == null) {
			this.roles = null;
		} else {
			this.roles = roles;
		}

	}
}
