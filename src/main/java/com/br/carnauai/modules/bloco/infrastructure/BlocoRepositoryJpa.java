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

    @Query("select b from Bloco b where (b.enderecoSaida is not null and b.enderecoSaida.id = :enderecoId) or (b.enderecoDispersao is not null and b.enderecoDispersao.id = :enderecoId)")
    List<Bloco> findByBairroId(@Param("enderecoId") UUID enderecoId);
}
