package Grupo_5_Gestion_de_un_Gimnasio.service;

import Grupo_5_Gestion_de_un_Gimnasio.model.Socio;
import Grupo_5_Gestion_de_un_Gimnasio.dto.SocioRequestDTO;
import Grupo_5_Gestion_de_un_Gimnasio.dto.SocioResponseDTO;
import Grupo_5_Gestion_de_un_Gimnasio.repository.SocioRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SocioServiceTest {

    @Mock
    private SocioRepository socioRepository;

    @InjectMocks
    private SocioService socioService;

    @Test
    void guardarSocioExitosamente() {
        Socio socio = new Socio();
        socio.setId(1L);

        SocioRequestDTO dto = new SocioRequestDTO();

        when(socioRepository.save(any(Socio.class))).thenReturn(socio);

        SocioResponseDTO respuesta = socioService.guardar(dto);

        assertNotNull(respuesta);
        verify(socioRepository, times(1)).save(any(Socio.class));
    }

    @Test
    void buscarSocioPorIdExitoso() {
        Socio socio = new Socio();
        socio.setId(1L);

        when(socioRepository.findById(1L)).thenReturn(Optional.of(socio));

        Optional<SocioResponseDTO> respuesta = socioService.obtenerPorId(1L);

        assertNotNull(respuesta);
        verify(socioRepository, times(1)).findById(1L);
    }
}
