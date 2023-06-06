package ec.edu.ups.msvccursos.models;

import javax.persistence.*;

@Entity
@Table(name = "cursos_usuarios")
public class CursoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", unique = true)
    private Long idUsuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(!(obj instanceof CursoUsuario)){
            return false;
        }
        CursoUsuario o = (CursoUsuario) obj;
        return this.idUsuario != null && this.idUsuario.equals(o.idUsuario);
    }
}
