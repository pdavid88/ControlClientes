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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional

public class PersonaTests {

    @Autowired
    private IPersonaService personaService;

    @Test
    public void testCrearPersona() {
        Persona nuevaPersona = new Persona();
        nuevaPersona.setNombre("Laura");
        nuevaPersona.setApellido("Gomez");
        nuevaPersona.setEmail("laura@example.com");
        nuevaPersona.setTelefono("123456789");
        nuevaPersona.setSaldo(1500.20);

        Persona guardarPersona = personaService.guardar(nuevaPersona);

        // Comprueba que la persona fue creada correctamente
        assertThat(guardarPersona).isNotNull();
        assertThat(guardarPersona.getNombre()).isEqualTo("Laura");
        assertThat(guardarPersona.getApellido()).isEqualTo("Gomez");
        assertThat(guardarPersona.getEmail()).isEqualTo("laura@example.com");
        assertThat(guardarPersona.getTelefono()).isEqualTo("123456789");
        assertThat(guardarPersona.getSaldo()).isEqualTo(1500.20);
    }

    @Test
    public void testActualizarPersona() {
        Persona personaOriginal = new Persona();
        personaOriginal.setNombre("Laura");
        personaOriginal.setApellido("Gomez");
        personaOriginal.setEmail("laura@example.com");
        personaOriginal.setTelefono("123456789");
        personaOriginal.setSaldo(1500.20);

        Persona guardarPersona = personaService.guardar(personaOriginal);

        // Comprueba que la persona fue creada correctamente
        assertThat(guardarPersona).isNotNull();
        assertThat(guardarPersona.getNombre()).isEqualTo("Laura");
        assertThat(guardarPersona.getEmail()).isEqualTo("laura@example.com");

        // Cambia los datos de la persona
        guardarPersona.setNombre("Laura Actualizada");
        guardarPersona.setTelefono("987654321");
        guardarPersona.setSaldo(2000.00);

        // Actualiza la persona
        Persona personaActualizada = personaService.guardar(guardarPersona);

        // Comprueba que la persona fue actualizada correctamente
        assertThat(personaActualizada).isNotNull();
        assertThat(personaActualizada.getNombre()).isEqualTo("Laura Actualizada");
        assertThat(personaActualizada.getTelefono()).isEqualTo("987654321");
        assertThat(personaActualizada.getSaldo()).isEqualTo(2000.00);

        // Comprueba que el email y el apellido siguen siendo los mismos
        assertThat(personaActualizada.getEmail()).isEqualTo("laura@example.com");
        assertThat(personaActualizada.getApellido()).isEqualTo("Gomez");
    }


    @Test
    public void testTelefonoOpcional() {
        Persona nuevaPersona = new Persona();
        nuevaPersona.setNombre("Laura");
        nuevaPersona.setApellido("Gomez");
        nuevaPersona.setEmail("laura@example.com");
        nuevaPersona.setSaldo(1500.20);

        Persona guardarPersona = personaService.guardar(nuevaPersona);

        assertThat(guardarPersona).isNotNull();
        assertThat(guardarPersona.getTelefono()).isNull();
    }

    @Test
    public void testTelefonoNumerico() {
        Persona persona = new Persona();
        String telefonoValido = "1234567890";
        persona.setTelefono(telefonoValido);
        assertEquals(telefonoValido, persona.getTelefono());
    }

    @Test
    public void testTelefonoNoNumerico() {
        Persona persona = new Persona();
        assertThrows(IllegalArgumentException.class, () -> {
            persona.setTelefono("123-456-7890");  // Este valor debe provocar una excepción
        });
    }

}
