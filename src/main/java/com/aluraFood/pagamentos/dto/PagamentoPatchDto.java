package com.aluraFood.pagamentos.dto;

import java.math.BigDecimal;
import java.util.Optional;

import com.aluraFood.pagamentos.model.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoPatchDto {
    private Optional<BigDecimal> valor = Optional.empty();
    private Optional<String> nome = Optional.empty();
    private Optional<String> numero = Optional.empty();
    private Optional<String> expiracao = Optional.empty();
    private Optional<String> codigo = Optional.empty();
    private Optional<Status> status = Optional.empty();
    private Optional<Long> formaDePagamentoId = Optional.empty();
    private Optional<Long> pedidoId = Optional.empty();
}
