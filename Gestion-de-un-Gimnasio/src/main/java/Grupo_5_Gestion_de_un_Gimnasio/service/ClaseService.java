package Grupo_5_Gestion_de_un_Gimnasio.service;

import Grupo_5_Gestion_de_un_Gimnasio.dto.ClaseRequestDTO;
import Grupo_5_Gestion_de_un_Gimnasio.dto.ClaseResponseDTO;
import Grupo_5_Gestion_de_un_Gimnasio.model.Clase;
import Grupo_5_Gestion_de_un_Gimnasio.model.Entrenador;
import Grupo_5_Gestion_de_un_Gimnasio.repository.ClaseRepository;
import Grupo_5_Gestion_de_un_Gimnasio.repository.EntrenadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClaseService {

    private final ClaseRepository claseRepository;
    private final EntrenadorRepository entrenadorRepository;

    public ClaseService(ClaseRepository claseRepository, EntrenadorRepository entrenadorRepository) {
        this.claseRepository = claseRepository;
        this.entrenadorRepository = entrenadorRepository;
    }

    public List<ClaseResponseDTO> obtenerTodas() {
        return claseRepository.findAll().stream()
                .map(this::converterAResponseDTO)
                .collect(Collectors.toList());
    }

    public ClaseResponseDTO guardar(ClaseRequestDTO dto) {
        Clase clase = new Clase();
        clase.setNombre(dto.getNombre());
        clase.setHorario(dto.getHorario());
        clase.setCupoMaximo(dto.getCupoMaximo());

        if (dto.getEntrenadorId() != null) {
            Entrenador entrenador = entrenadorRepository.findById(dto.getEntrenadorId())
                    .orElseThrow(() -> new RuntimeException("Entrenador no encontrado"));
            clase.setEntrenador(entrenador);
        }

        Clase guardada = claseRepository.save(clase);
        return converterAResponseDTO(guardada);
    }

    public ClaseResponseDTO actualizar(Long id, ClaseRequestDTO dto) {
        Clase clase = claseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada"));

        clase.setNombre(dto.getNombre());
        clase.setHorario(dto.getHorario());
        clase.setCupoMaximo(dto.getCupoMaximo());

        if (dto.getEntrenadorId() != null) {
            Entrenador entrenador = entrenadorRepository.findById(dto.getEntrenadorId())
                    .orElseThrow(() -> new RuntimeException("Entrenador no encontrado"));
            clase.setEntrenador(entrenador);
        }

        return converterAResponseDTO(claseRepository.save(clase));
    }

    public void eliminar(Long id) {
        claseRepository.deleteById(id);
    }

    private ClaseResponseDTO converterAResponseDTO(Clase c) {
        ClaseResponseDTO dto = new ClaseResponseDTO();
        dto.setId(c.getId());
        dto.setNombre(c.getNombre());
        dto.setHorario(c.getHorario());
        dto.setCupoMaximo(c.getCupoMaximo());
        if (c.getEntrenador() != null) {
            dto.setNombreEntrenador((String) c.getEntrenador().getNombre());
        }
        return dto;
    }
}