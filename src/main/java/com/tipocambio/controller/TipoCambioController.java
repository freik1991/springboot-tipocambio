package com.tipocambio.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tipocambio.bean.TipoCambio;
import com.tipocambio.repository.TipoCambioRepository;
import com.tipocambio.repository.UsuarioRepository;
import com.tipocambio.request.TipoCambioRequest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v2")
public class TipoCambioController {

	@Autowired
	private TipoCambioRepository tipoCambioRepository;

	@GetMapping("/listarTipoCambio")
	public List<TipoCambio> listarTipoCambio() {
		List<TipoCambio> lstTipoCambio = new ArrayList<TipoCambio>();
		tipoCambioRepository.findAll().forEach(tipocambio1 -> lstTipoCambio.add(tipocambio1));
		return lstTipoCambio;
	}

//
	@GetMapping("/tipocambio/{id}")
	public TipoCambio obtenerTipoCambioxId(@PathVariable int id) {
		return tipoCambioRepository.findById(id).get();
	}

	@PostMapping("/savetipocambio")
	public TipoCambio saveTipoCambio(@RequestBody TipoCambio tipocambio) {
		TipoCambio bean = new TipoCambio();
		try {
			double monto_tipo_cambio = tipocambio.getMonto() * tipocambio.getTipo_cambio();
			bean.setId(tipocambio.getId());
			bean.setMonto(tipocambio.getMonto());
			bean.setMoneda_origen(tipocambio.getMoneda_origen());
			bean.setMoneda_destino(tipocambio.getMoneda_destino());
			bean.setTipo_cambio(tipocambio.getTipo_cambio());
			bean.setMonto_tipo_cambio(monto_tipo_cambio);
			bean.setUsuario_creacion("admin");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String fechaComoCadena = sdf.format(new Date());
			bean.setFecha_creacion(fechaComoCadena);
			return tipoCambioRepository.save(bean);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return bean;
	}

	@PutMapping("/updateTipocambio/{id}")
	public TipoCambio actualizarTipoCambio(@RequestBody TipoCambioRequest tipocambio, @PathVariable int id) {
		TipoCambio encontrado = tipoCambioRepository.findById(id).get();
		double monto_tipo_cambio = tipocambio.getMonto() * tipocambio.getTipo_cambio();
		TipoCambio bean = new TipoCambio();
		bean.setMonto(tipocambio.getMonto());
		bean.setMoneda_origen(tipocambio.getMoneda_origen());
		bean.setMoneda_destino(tipocambio.getMoneda_destino());
		bean.setTipo_cambio(tipocambio.getTipo_cambio());
		bean.setMonto_tipo_cambio(monto_tipo_cambio);
		bean.setUsuario_creacion(encontrado.getUsuario_creacion());
		bean.setFecha_creacion(encontrado.getFecha_creacion());
		bean.setUsuario_modificacion("admin");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaComoCadena = sdf.format(new Date());
		bean.setFecha_modificacion(fechaComoCadena);
		bean.setId(id);
		return tipoCambioRepository.save(bean);
	}
}
