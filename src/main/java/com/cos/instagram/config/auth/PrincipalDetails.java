package com.cos.instagram.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.cos.instagram.domain.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User{

	private static final long serialVersionUID = 1L;

	private User user;
	private Map<String, Object> attributes;
	
	//일반 로그인 용 생성자
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	// OAuth 로그인 용 생성자
	public PrincipalDetails(User user, Map<String, Object> attributes) {
		this.user = user;
		this.attributes = attributes;
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {  //비밀번호 완료 되었는지
		return true;
	}

	@Override
	public boolean isEnabled() { //계정 활성화 되있는지
		return true;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<GrantedAuthority>();
		collection.add(()-> user.getRole().getKey());
		return collection;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public String getName() {
		return user.getProviderId();
	}
		

}
