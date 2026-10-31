package Grupo_5_Gestion_de_un_Gimnasio.model; //[span_13](start_span)[span_13](end_span)

import jakarta.persistence.*; //[span_14](start_span)[span_14](end_span)
        import lombok.*; //[span_15](start_span)[span_15](end_span)

@Entity //[span_16](start_span)[span_16](end_span)
@Table(name = "socio") //[span_17](start_span)[span_17](end_span)
@Data //[span_18](start_span)[span_18](end_span)
@NoArgsConstructor //[span_19](start_span)[span_19](end_span)
@AllArgsConstructor //[span_20](start_span)[span_20](end_span)
@Builder //[span_21](start_span)[span_21](end_span)
public class Socio {

    @Id //[span_22](start_span)[span_22](end_span)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //[span_23](start_span)[span_23](end_span)
    private Long id; //[span_24](start_span)[span_24](end_span)

    private String nombre; //[span_25](start_span)[span_25](end_span)
    private String apellido; // <-- AÑADIR ESTA LÍNEA

    private String email; //[span_26](start_span)[span_26](end_span)
    private String telefono; //[span_27](start_span)[span_27](end_span)
}