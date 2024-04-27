package com.david.servicio;

import com.david.domain.Persona;

import java.util.List;

public interface IPersonaService {

    public List<Persona> listarPersonas();

    public void guardar(Persona persona);

    public void eliminar(Persona persona);

    public Persona buscarPersona(Persona persona);

}
