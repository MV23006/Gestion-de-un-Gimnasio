package Grupo_5_Gestion_de_un_Gimnasio.controller;

import Grupo_5_Gestion_de_un_Gimnasio.model.Entrenador;
import Grupo_5_Gestion_de_un_Gimnasio.repository.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/entrenadores")
public class EntrenadorController {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    @GetMapping
    public List<Entrenador> obtenerTodos() {
        return entrenadorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrenador> obtenerPorId(@PathVariable Long id) {
        return entrenadorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Entrenador crearEntrenador(@RequestBody Entrenador entrenador) {
        return entrenadorRepository.save(entrenador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrenador> actualizarEntrenador(@PathVariable Long id, @RequestBody Entrenador detalles) {
        return entrenadorRepository.findById(id).map(entrenador -> {
            entrenador.setNombre(detalles.getNombre());
            entrenador.setApellido(detalles.getApellido());
            entrenador.setEspecialidad(detalles.getEspecialidad());
            entrenador.setEmail(detalles.getEmail());
            return ResponseEntity.ok(entrenadorRepository.save(entrenador));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEntrenador(@PathVariable Long id) {
        if (entrenadorRepository.existsById(id)) {
            entrenadorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}