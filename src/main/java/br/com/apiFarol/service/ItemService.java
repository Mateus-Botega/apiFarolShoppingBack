package br.com.apiFarol.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.common.base.Preconditions;

import br.com.apiFarol.controller.RegistroNaoEncontradoException;
import br.com.apiFarol.entity.Item;
import br.com.apiFarol.repository.ItemRepository;

@Service
@Validated
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	public Item inserir(@Valid @NotNull(message = "O novo item é obrigatório") Item novoItem) {
		Preconditions.checkArgument(novoItem.getId() == null, "O ID do novo item deve ser nulo");
		Item itemSalvo = itemRepository.save(novoItem);
		return itemSalvo;
	}

	public Item alterar(@Valid @NotNull(message = "O novo item é obrigatório") Item itemSalvo) {
		Preconditions.checkArgument(itemSalvo.getId() != null, "O ID do item não deve ser nulo");
		Item itemAtualizado = itemRepository.save(itemSalvo);
		return itemAtualizado;
	}

	public void exclui(@RequestBody Item item) {
		itemRepository.delete(item);
	}

	public Item buscarPorID(@NotNull(message = "O ID para busca não deve ser nulo") Integer id) {
		Item itemEncontrado = itemRepository.buscarPor(id);
		if (itemEncontrado == null) {
			throw new RegistroNaoEncontradoException("Não foi encontrado nenhum item com esse ID");
		}
		return itemEncontrado;
	}

	public List<Item> listaAllItens() {
		return itemRepository.findAll();
	}

}
