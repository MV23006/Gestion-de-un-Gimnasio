package Grupo_5_Gestion_de_un_Gimnasio.dto;

import Grupo_5_Gestion_de_un_Gimnasio.model.MetodoPago;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PagoResponseDTO {
    private Long id;
    private Long socioId;
    private String nombreSocio;
    private BigDecimal monto;
    private LocalDate fechaPago;
    private MetodoPago metodoPago;
}
