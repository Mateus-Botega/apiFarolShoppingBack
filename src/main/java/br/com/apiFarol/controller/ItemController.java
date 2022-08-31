package br.com.apiFarol.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.apiFarol.entity.Item;
import br.com.apiFarol.service.ItemService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ItemController {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private MapConverter mapConverter;

	@Autowired
	private ItemService itemService;

	@PostMapping("/item")
	public ResponseEntity<?> inserir(@RequestBody Map<String, Object> itemMap) {
		Item novoItem = mapper.convertValue(itemMap, Item.class);
		Item itemSalvo = itemService.inserir(novoItem);
		return ResponseEntity.created(URI.create("item/id/" + itemSalvo.getId())).build();
	}

	@PutMapping("/item")
	public ResponseEntity<?> alterar(@RequestBody Map<String, Object> itemMap) {
		Item itemSalvo = mapper.convertValue(itemMap, Item.class);
		Item itemAtualizado = itemService.alterar(itemSalvo);
		return ResponseEntity.ok(mapConverter.toJsonMap(itemAtualizado));
	}

	@DeleteMapping("/item")
	public void deletaUmItem(@RequestBody Item item) {
		itemService.exclui(item);
	}

	@GetMapping("/item/{id}")
	public ResponseEntity<?> buscarPor(@PathVariable("id") Integer id) throws JsonProcessingException {
		Item itemEncontrado = itemService.buscarPorID(id);
		String json = mapper.writeValueAsString(itemEncontrado);
		JSONObject jsonObj = new JSONObject(json);
		return ResponseEntity.ok(jsonObj.toMap());
	}

	@GetMapping("/itens")
	public ResponseEntity<List<Item>> listaTodosOsItens() {
		return ResponseEntity.ok(this.itemService.listaAllItens());
	}

}
