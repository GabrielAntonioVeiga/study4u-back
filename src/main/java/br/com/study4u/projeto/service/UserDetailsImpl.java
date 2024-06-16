package br.com.study4u.projeto.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.study4u.projeto.entity.UsuarioEntity;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nome;
	
	private String email;
	
	private String senha;

	public UserDetailsImpl(Long id, String nome, String email, String senha,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(UsuarioEntity usuario) {
		return new UserDetailsImpl(
				usuario.getId(), 
				usuario.getNome(), 
				usuario.getEmail(), 
				usuario.getSenha(),
				new ArrayList<>());
	}
	
	private Collection<? extends GrantedAuthority> authorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}
	
	public String getEmail() {
		return email;
	}

	@Override
	public String getUsername() {
		return nome;
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
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Long getId() {
		return id;
	}

}
