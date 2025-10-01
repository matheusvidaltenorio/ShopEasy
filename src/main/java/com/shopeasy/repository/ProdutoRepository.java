package com.shopeasy.repository;

import com.shopeasy.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    // JpaRepository já dá todos os métodos CRUD: save, findAll, findById, deleteById
}
