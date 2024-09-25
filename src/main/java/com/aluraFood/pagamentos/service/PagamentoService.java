package com.aluraFood.pagamentos.service;

import com.aluraFood.pagamentos.dto.PagamentoDto;
import com.aluraFood.pagamentos.dto.PagamentoPatchDto;
import com.aluraFood.pagamentos.model.Pagamento;
import com.aluraFood.pagamentos.model.Status;
import com.aluraFood.pagamentos.repository.PagamentoRepositoy;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepositoy repository;

    @Autowired
    private ModelMapper modelMapper;


    public Page<PagamentoDto> obterTodos(Pageable paginacao) {
        return repository
                .findAll(paginacao)
                .map(p -> modelMapper.map(p, PagamentoDto.class));
    }

    public PagamentoDto obterPorId(Long id) {
        Pagamento pagamento = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public PagamentoDto criarPagamento(PagamentoDto dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setStatus(Status.CRIADO);
        repository.save(pagamento);

        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public PagamentoDto atualizarPagamento(Long id, PagamentoDto dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setId(id);
        pagamento = repository.save(pagamento);
        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public void excluirPagamento(Long id) {
        repository.deleteById(id);
    }

    public PagamentoDto patchPagameto(Long  id, PagamentoPatchDto targetPagamentoDto) {
        Pagamento existingPagamento = repository.findById(id).orElseThrow();

        targetPagamentoDto.getValor().ifPresent(existingPagamento::setValor);
        targetPagamentoDto.getNome().ifPresent(existingPagamento::setNome);
        targetPagamentoDto.getNumero().ifPresent(existingPagamento::setNumero);
        targetPagamentoDto.getExpiracao().ifPresent(existingPagamento::setExpiracao);
        targetPagamentoDto.getCodigo().ifPresent(existingPagamento::setCodigo);
        targetPagamentoDto.getStatus().ifPresent(existingPagamento::setStatus);
        targetPagamentoDto.getFormaDePagamentoId().ifPresent(existingPagamento::setFormaDePagamentoId);
        targetPagamentoDto.getPedidoId().ifPresent(existingPagamento::setPedidoId);

        existingPagamento = repository.save(existingPagamento);
        return modelMapper.map(existingPagamento, PagamentoDto.class);
    }



}

