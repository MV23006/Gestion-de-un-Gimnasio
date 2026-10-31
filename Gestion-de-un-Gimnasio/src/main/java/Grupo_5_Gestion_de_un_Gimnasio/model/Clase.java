package Grupo_5_Gestion_de_un_Gimnasio.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clases")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String horario;

    @Column(nullable = false)
    private Integer cupoMaximo;

    @ManyToOne
    @JoinColumn(name = "entrenador_id")
    private Entrenador entrenador;

    public Object getDescripcion() {
        return null;
    }

    public void setDescripcion(Object descripcion) {

    }
}
