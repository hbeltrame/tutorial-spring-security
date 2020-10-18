package com.empresa.cursospringsecurity.models.enums;

public enum Role {

	// o prefixo "ROLE_" é exigência do Spring Security
	ADMIN(1, "ROLE_ADMIN"),
	EMPLOYEE(2, "ROLE_EMPLOYEE");
	
	private int cod;
	private String description;
	
	private Role(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static Role toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (Role x : Role.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	 
}
