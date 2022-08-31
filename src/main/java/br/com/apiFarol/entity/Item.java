package br.com.apiFarol.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.apiFarol.enums.Disponibilidade;
import br.com.apiFarol.enums.Status;
import lombok.Data;

@Data
@Entity(name = "Item")
@Table(name = "item")
public class Item implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "codigo",unique = true)
	@NotEmpty(message = "O codigo do item do inventário não deve ser vazio")
	@NotBlank(message = "O codigo do item do inventário deve possuir pelo menos 1 caracter")
	@Size(min = 1, max = 7, 
		message = "O codigo do item do inventário não deve possuir mais do que 7 caracteres")
	private String codigo;
	
	@Column(name = "nr_serie")
	@NotNull(message = "O número de série é obrigatório")
	private Integer nrSerie;
	
	@Column(name = "disponibilidade")
	@Enumerated(EnumType.STRING)
	@NotNull(message = "A disponibilidade é obrigatória")
	private Disponibilidade disponibilidade;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	@NotNull(message = "O status é obrigatório")
	private Status status;
	
	@Column(name = "descricao")
	@NotEmpty(message = "A descrição não deve ser nula")
	@NotBlank(message = "A descrição deve possuir pelo menos 1 caracter")
	@Size(min = 1, max = 100, message = "A descrição não deve possuir mais de 100 caracteres")
	private String descricao;

	@Column(name = "localizacao")
	@NotBlank(message = "A localização não deve ser nula")
	@NotEmpty(message = "A localização deve possuir pelo menos 3 caracteres")
	@Size(min = 3, max = 250, message = "A localização deve possuir de 3 a 250 caracteres")
	private String localizacao;
	
	@Column(name = "dt_movimentacao")
	@NotNull(message = "A data de movimentação é obrigatória")
	@FutureOrPresent(message = "A data de movimentação não deve ser anterior a data atual")
	private LocalDateTime dtMovimentacao;
	
}
