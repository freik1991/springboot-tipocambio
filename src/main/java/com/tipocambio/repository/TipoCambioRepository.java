package com.tipocambio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tipocambio.bean.TipoCambio;


@Repository
public interface  TipoCambioRepository  extends CrudRepository<TipoCambio, Integer>{
}
