package Grupo_5_Gestion_de_un_Gimnasio.controller;

import Grupo_5_Gestion_de_un_Gimnasio.model.*;
import Grupo_5_Gestion_de_un_Gimnasio.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private SocioRepository socioRepository;

    @GetMapping
    public List<Inscripcion> obtenerTodas() {
        return inscripcionRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> inscribirSocio(@RequestParam Long socioId, @RequestParam Long claseId) {
        Clase clase = claseRepository.findById(claseId)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));
        Socio socio = socioRepository.findById(socioId)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado"));

        // Lógica de negocio: Validar cupo
        long inscritosActuales = inscripcionRepository.countByClaseId(claseId);
        if (inscritosActuales >= clase.getCupoMaximo()) {
            return ResponseEntity.badRequest().body("Error: La clase ha alcanzado su cupo máximo.");
        }

        Inscripcion nuevaInscripcion = new Inscripcion();
        nuevaInscripcion.setSocio(socio);
        nuevaInscripcion.setClase(clase);
        nuevaInscripcion.setAsistio(false);

        return ResponseEntity.ok(inscripcionRepository.save(nuevaInscripcion));
    }

    @PutMapping("/{id}/asistencia")
    public ResponseEntity<?> registrarAsistencia(@PathVariable Long id, @RequestParam Boolean asistio) {
        return inscripcionRepository.findById(id).map(inscripcion -> {
            inscripcion.setAsistio(asistio);
            return ResponseEntity.ok(inscripcionRepository.save(inscripcion));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarInscripcion(@PathVariable Long id) {
        if (inscripcionRepository.existsById(id)) {
            inscripcionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}