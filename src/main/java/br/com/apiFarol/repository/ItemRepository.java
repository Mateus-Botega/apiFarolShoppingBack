package br.com.apiFarol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.apiFarol.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{

	@Query(value =
			"SELECT i "
			+ "FROM Item i "
			+ "WHERE i.id = :id")
	Item buscarPor(@Param("id") Integer id);
	
}
