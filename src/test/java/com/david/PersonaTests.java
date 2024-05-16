package com.david;

import com.david.domain.Persona;
import com.david.servicio.IPersonaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional

public class PersonaTests {

    @Autowired
    private IPersonaService personaService;

    @Test
    public void testGuardarPersona() {
        Persona nuevaPersona = new Persona();
        nuevaPersona.setNombre("Laura");
        nuevaPersona.setApellido("Gomez");
        nuevaPersona.setEmail("laura@example.com");
        nuevaPersona.setTelefono("123456789");
        nuevaPersona.setSaldo(1500.20);

        Persona guardarPersona = personaService.guardar(nuevaPersona);

        assertThat(guardarPersona).isNotNull();
        assertThat(guardarPersona.getNombre()).isEqualTo("Laura");
        assertThat(guardarPersona.getEmail()).isEqualTo("laura@example.com");
    }
}
