package com.david;

import com.david.domain.Persona;
import com.david.servicio.IPersonaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextClosedEvent;

@SpringBootApplication
public class HolaSpringApplication {

    private static Persona personaPrueba;  // Guardar referencia a nivel de clase

    public static void main(String[] args) {
        SpringApplication.run(HolaSpringApplication.class, args);
    }

    @Bean
    public CommandLineRunner prueba(IPersonaService personaService) {
        return (args) -> {
            // Crear una nueva persona
            Persona persona = new Persona();
            persona.setNombre("David");
            persona.setApellido("Prueba");
            persona.setEmail("david@example.com");
            persona.setTelefono("123456789");
            persona.setSaldo(150.5);

            // Guardar la persona
            personaPrueba = personaService.guardar(persona);
            System.out.println("Persona guardada con ID: " + personaPrueba.getIdPersona());
        };
    }

    // Cuando el contexto de Spring se cierra (cuando el servidor se apaga), este listener es invocado.
    // En este método, verificamos si existe una personaGuardada y, de ser así, la eliminamos.
    @Bean
    public ApplicationListener<ContextClosedEvent> contextClosedEventListener(IPersonaService personaService) {
        return event -> {
            if (personaPrueba != null) {
                personaService.eliminar(personaPrueba);
                System.out.println("Persona eliminada con ID: " + personaPrueba.getIdPersona());
            }
        };
    }

}
