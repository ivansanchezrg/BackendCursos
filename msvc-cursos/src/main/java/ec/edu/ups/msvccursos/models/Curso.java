package ec.edu.ups.msvccursos.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private String nombreInstructor;
    private double costo;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "curso_id")
    private List<CursoUsuario> cursoUsuarios;
    @Transient  //Indica que no se va a implementar este atributo como un campo en la base de datos.
    private List<Usuario> usuarios;

    public Curso() {
        cursoUsuarios = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreInstructor() {
        return nombreInstructor;
    }

    public void setNombreInstructor(String nombreInstructor) {
        this.nombreInstructor = nombreInstructor;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public List<CursoUsuario> getCursoUsuarios() {
        return cursoUsuarios;
    }

    public void addCursoUsuario(CursoUsuario cursoUsuario){
        cursoUsuarios.add(cursoUsuario);
    }

    public  void deleteCursoUsuario(CursoUsuario cursoUsuario){
        cursoUsuarios.remove(cursoUsuario);
    }

    public void setCursoUsuarios(List<CursoUsuario> cursoUsuarios) {
        this.cursoUsuarios = cursoUsuarios;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
