package br.com.study4u.projeto.dto;

import lombok.Data;

@Data
public class AcessDto {
	
	private String token;
	private Long userId;

	public AcessDto(String token, Long userId) {
		super();
		this.token = token;
		this.userId = userId;
		
	}
	
	
}
