package Grupo_5_Gestion_de_un_Gimnasio.controller;

import Grupo_5_Gestion_de_un_Gimnasio.model.Clase;
import Grupo_5_Gestion_de_un_Gimnasio.repository.ClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clases")
public class ClaseController {

    @Autowired
    private ClaseRepository claseRepository;

    @GetMapping
    public List<Clase> obtenerTodas() {
        return claseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clase> obtenerPorId(@PathVariable Long id) {
        return claseRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Clase crearClase(@RequestBody Clase clase) {
        return claseRepository.save(clase);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clase> actualizarClase(@PathVariable Long id, @RequestBody Clase detalles) {
        return claseRepository.findById(id).map(clase -> {
            clase.setNombre(detalles.getNombre());
            clase.setDescripcion(detalles.getDescripcion());
            clase.setCupoMaximo(detalles.getCupoMaximo());
            clase.setHorario(detalles.getHorario());
            clase.setEntrenador(detalles.getEntrenador());
            return ResponseEntity.ok(claseRepository.save(clase));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarClase(@PathVariable Long id) {
        if (claseRepository.existsById(id)) {
            claseRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}