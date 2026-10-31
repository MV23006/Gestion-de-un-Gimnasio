package Grupo_5_Gestion_de_un_Gimnasio.controller;

import Grupo_5_Gestion_de_un_Gimnasio.model.Socio;
import Grupo_5_Gestion_de_un_Gimnasio.repository.SocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/socios")
public class SocioController {

    @Autowired
    private SocioRepository socioRepository;

    @GetMapping
    public List<Socio> obtenerTodos() {
        return socioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Socio> obtenerPorId(@PathVariable Long id) {
        return socioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Socio crearSocio(@RequestBody Socio socio) {
        return socioRepository.save(socio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Socio> actualizarSocio(@PathVariable Long id, @RequestBody Socio detalles) {
        return socioRepository.findById(id).map(socio -> {
            socio.setNombre(detalles.getNombre());
            socio.setApellido(detalles.getApellido());
            socio.setEmail(detalles.getEmail());
            socio.setTelefono(detalles.getTelefono());
            return ResponseEntity.ok(socioRepository.save(socio));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSocio(@PathVariable Long id) {
        if (socioRepository.existsById(id)) {
            socioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}