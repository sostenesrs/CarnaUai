package com.br.carnauai.modules.bloco.infrastructure;

import com.br.carnauai.modules.bloco.domain.Bloco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface BlocoRepositoryJpa extends JpaRepository<Bloco, UUID> {

    List<Bloco> findByData(LocalDate data);

    List<Bloco> findByOidAndDataGreaterThanEqual(String oid, LocalDate data);

    List<Bloco> findByOidAndDataGreaterThanEqualAndIdNot(String oid, LocalDate data, UUID id);

    @Query("""
        select b from Bloco b
        left join b.enderecoSaida es
        left join b.enderecoDispersao ed
        where (:data is null or b.data = :data)
          and (:bairro is null or lower(es.bairro) = lower(:bairro) or lower(ed.bairro) = lower(:bairro))
          and (:nome is null or lower(b.nome) like lower(concat('%', :nome, '%')))
        """)
    List<Bloco> findWithFilters(@Param("data") LocalDate data, @Param("bairro") String bairro, @Param("nome") String nome);
}
