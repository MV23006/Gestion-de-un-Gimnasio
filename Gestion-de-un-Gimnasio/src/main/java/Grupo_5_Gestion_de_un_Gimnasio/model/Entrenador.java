package Grupo_5_Gestion_de_un_Gimnasio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "entrenadores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String especialidad;
    private String email;
}