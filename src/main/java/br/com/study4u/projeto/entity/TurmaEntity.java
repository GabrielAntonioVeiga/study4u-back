package br.com.study4u.projeto.entity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.study4u.projeto.dto.TurmaDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "turma")
public class TurmaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min = 3, max = 45)
	private String titulo;
	
	@NotBlank
	@Size(min = 3, max = 100)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name="criador_id")
	private UsuarioEntity criador;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	    name = "usuario_turma",
	    joinColumns = @JoinColumn(name = "turma_id"),
	    inverseJoinColumns = @JoinColumn(name = "usuario_id")
	)
	@JsonManagedReference
	@JsonIgnore
	private Set<UsuarioEntity> usuarios = new HashSet<UsuarioEntity>() ;
	
	public TurmaEntity() {
		super();
	}
	
	public TurmaEntity(TurmaDto dto) {
		BeanUtils.copyProperties(dto, this);
	}
	
	public void atribuirUsuario(UsuarioEntity usuario) {
        this.usuarios.add(usuario);
        usuario.getTurmas().add(this);
    }

    @Transactional
    public void removerUsuario(UsuarioEntity usuario) {
        this.usuarios.remove(usuario);
        usuario.getTurmas().remove(this);
    }
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TurmaEntity that = (TurmaEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TurmaEntity{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", criador=" + (criador != null ? criador.getId() : null) +
                '}';
    }
	
}
