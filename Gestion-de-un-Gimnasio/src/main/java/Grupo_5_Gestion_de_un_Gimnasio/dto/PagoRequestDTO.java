package Grupo_5_Gestion_de_un_Gimnasio.dto;

import Grupo_5_Gestion_de_un_Gimnasio.model.MetodoPago;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PagoRequestDTO {
    private Long socioId;
    private BigDecimal monto;
    private LocalDate fechaPago;
    private MetodoPago metodoPago;
}