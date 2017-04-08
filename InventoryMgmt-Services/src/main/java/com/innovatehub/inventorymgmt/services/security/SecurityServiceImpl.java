package com.innovatehub.inventorymgmt.services.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.innovatehub.inventorymgmt.common.entity.security.Role;
import com.innovatehub.inventorymgmt.common.entity.security.Screen;
import com.innovatehub.inventorymgmt.common.repository.security.RoleRepository;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	public RoleRepository getRoleRepository() {
		return roleRepository;
	}

	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	public String getCurrentUserName() {
		Object details = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(details instanceof User) {
			return ((User)details).getUsername();
		}
		
		return null;
	}

	@Override
	public Set<Role> getUserRoles(String userName) {
		Set<Role> userRoles = new HashSet<Role>();
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
		
		for(GrantedAuthority authority: userDetails.getAuthorities()) {
			userRoles.add(roleRepository.findByName(authority.getAuthority()));
		}
		
		return userRoles;
	}

	@Override
	public List<Screen> getUserScreens(Set<Role> roles) {
		List<List<Screen>> userRoles = roles.stream().map(role -> role.getScreens()).collect(Collectors.toList());
		return userRoles.stream().flatMap(screenSet -> screenSet.stream()).collect(Collectors.toList());
	}

	@Override
	public List<Screen> getUserScreens(String userName) {
		Set<Role> userRoles = this.getUserRoles(userName);
		return this.getUserScreens(userRoles);
	}
	
	
}
