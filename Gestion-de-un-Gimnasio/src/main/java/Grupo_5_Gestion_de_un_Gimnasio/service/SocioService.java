package Grupo_5_Gestion_de_un_Gimnasio.service;

import Grupo_5_Gestion_de_un_Gimnasio.dto.SocioRequestDTO;
import Grupo_5_Gestion_de_un_Gimnasio.dto.SocioResponseDTO;
import Grupo_5_Gestion_de_un_Gimnasio.model.Socio;
import Grupo_5_Gestion_de_un_Gimnasio.repository.SocioRepository;

// Si creaste un paquete dto, impórtalos aquí. Si están en el mismo paquete service, se detectan automáticos.
// import Grupo_5_Gestion_de_un_Gimnasio.dto.SocioRequestDTO;
// import Grupo_5_Gestion_de_un_Gimnasio.dto.SocioResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class SocioService {

    @Autowired
    private SocioRepository socioRepository;

    public List<SocioResponseDTO> obtenerTodos() {
        return socioRepository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    public SocioResponseDTO guardar(SocioRequestDTO dto) {
        Socio socio = new Socio();
        socio.setNombre(dto.getNombre());
        socio.setApellido(dto.getApellido());
        socio.setEmail(dto.getEmail());
        socio.setTelefono(dto.getTelefono());

        Socio guardado = socioRepository.save(socio);
        return convertirADto(guardado);
    }

    public Optional<SocioResponseDTO> actualizar(Long id, SocioRequestDTO dto) {
        return socioRepository.findById(id).map(socioExistente -> {
            socioExistente.setNombre(dto.getNombre());
            socioExistente.setApellido(dto.getApellido());
            socioExistente.setEmail(dto.getEmail());
            socioExistente.setTelefono(dto.getTelefono());

            Socio actualizado = socioRepository.save(socioExistente);
            return convertirADto(actualizado);
        });
    }

    public boolean eliminar(Long id) {
        if (socioRepository.existsById(id)) {
            socioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private SocioResponseDTO convertirADto(Socio socio) {
        SocioResponseDTO dto = new SocioResponseDTO();
        dto.setId(socio.getId());
        dto.setNombre(socio.getNombre());
        dto.setApellido(socio.getApellido());
        dto.setEmail(socio.getEmail());
        dto.setTelefono(socio.getTelefono());
        return dto;
    }

    public Optional<SocioResponseDTO> obtenerPorId(Long id) {
        return socioRepository.findById(id)
                .map(this::convertirADto);
    }
}
