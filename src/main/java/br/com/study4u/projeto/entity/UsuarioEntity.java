package br.com.study4u.projeto.entity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.study4u.projeto.dto.UsuarioDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class UsuarioEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min = 3, max = 45)
	private String nome;
	
	@NotBlank
	private String email;
	
	@NotBlank
	@Size(min = 8)
	private String senha;
	
	@ManyToMany(mappedBy = "usuarios")
	@JsonBackReference
	private Set<TurmaEntity> turmas = new HashSet<TurmaEntity>();

	public UsuarioEntity() {
		super();
	}

	public UsuarioEntity(UsuarioDto dto) {
		BeanUtils.copyProperties(dto, this);
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioEntity that = (UsuarioEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UsuarioEntity{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

	public void removerTurma(TurmaEntity turma) {
		this.turmas.remove(turma);
		
	}
	
}
