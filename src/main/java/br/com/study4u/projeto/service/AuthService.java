package br.com.study4u.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.study4u.projeto.dto.AcessDto;
import br.com.study4u.projeto.dto.AuthenticationDto;
import br.com.study4u.projeto.security.jwt.JwtUtils;

@Service
public class AuthService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;

	public AcessDto login(AuthenticationDto authDto) {
		
		try {
			
		UsernamePasswordAuthenticationToken userAuth = 
				new UsernamePasswordAuthenticationToken(authDto.getEmail(), authDto.getSenha());
		
		Authentication authentication = authenticationManager.authenticate(userAuth);
		
		UserDetailsImpl userAuthenticate = (UserDetailsImpl)authentication.getPrincipal();
		
		String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);
		
		Long userId = userAuthenticate.getId();
		
		AcessDto acessDto = new AcessDto(token, userId);
		
		return acessDto;
		} catch (BadCredentialsException e) {
			// TODO LOGIN OU SENHA INVALIDA
		}
		return new AcessDto("Acesso negado", null);
	}
}
