package com.example.demo.dto;

public class PersonaDto {
	 private int persona_id;
	    private String nombre;
	    private String telefono;
	    private String identificacion;


	    public int getPersona_id() {
	        return persona_id;
	    }

	    public void setPersona_id(int persona_id) {
	        this.persona_id = persona_id;
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public String getTelefono() {
	        return telefono;
	    }

	    public void setTelefono(String telefono) {
	        this.telefono = telefono;
	    }

	    public String getIdentificacion() {
	        return identificacion;
	    }

	    public void setIdentificacion(String identificacion) {
	        this.identificacion = identificacion;
	    }
}
