package com.tipocambio.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.tipocambio.bean.TipoCambio;

import reactor.core.publisher.Mono;

@Repository
public interface  TipoCambioRepository  extends ReactiveCrudRepository<TipoCambio, Long>{
}
