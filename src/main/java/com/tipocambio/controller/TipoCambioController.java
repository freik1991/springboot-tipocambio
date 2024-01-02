package com.tipocambio.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@GetMapping("/tipocambio")
	public Flux<TipoCambio> listarTipoCambio() {
		return tipoCambioRepository.findAll();
	}

	@GetMapping("/tipocambio/{id}")
	public Mono<ResponseEntity<TipoCambio>> obtenerTipoCambioxId(@PathVariable long id) {
		return tipoCambioRepository.findById(id).map(tipocambio -> new ResponseEntity<>(tipocambio, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

	}
	
	@PostMapping("/tipocambio")
	public Mono<ResponseEntity<TipoCambio>> saveTipoCambio(@RequestBody TipoCambioRequest tipocambio) {
		double monto_tipo_cambio = tipocambio.getMonto() * tipocambio.getTipo_cambio();
		TipoCambio bean = new TipoCambio();
		bean.setMonto(tipocambio.getMonto());
		bean.setMoneda_origen(tipocambio.getMoneda_origen());
		bean.setMoneda_destino(tipocambio.getMoneda_destino());
		bean.setTipo_cambio(tipocambio.getTipo_cambio());
		bean.setMonto_tipo_cambio(monto_tipo_cambio);
		bean.setUsuario_creacion("admin");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaComoCadena = sdf.format(new Date());
		bean.setFecha_creacion(fechaComoCadena);
		return tipoCambioRepository.save(bean)
				.map(usuarioGuardado -> new ResponseEntity<>(usuarioGuardado, HttpStatus.ACCEPTED))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE));
	}

	@PutMapping("/tipocambio/{id}")
	public Mono<ResponseEntity<TipoCambio>> actualizarTipoCambio(@RequestBody TipoCambioRequest tipocambio, @PathVariable long id) {
		return tipoCambioRepository.findById(id).flatMap(usuarioActualizado -> {
			tipocambio.setId(id);
			double monto_tipo_cambio = tipocambio.getMonto() * tipocambio.getTipo_cambio();
			TipoCambio bean = new TipoCambio();
			bean.setMonto(tipocambio.getMonto());
			bean.setMoneda_origen(tipocambio.getMoneda_origen());
			bean.setMoneda_destino(tipocambio.getMoneda_destino());
			bean.setTipo_cambio(tipocambio.getTipo_cambio());
			bean.setMonto_tipo_cambio(monto_tipo_cambio);
			bean.setUsuario_creacion(usuarioActualizado.getUsuario_creacion());
			bean.setFecha_creacion(usuarioActualizado.getFecha_creacion());
			bean.setUsuario_modificacion("admin");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String fechaComoCadena = sdf.format(new Date());
			bean.setFecha_modificacion(fechaComoCadena);
			bean.setId(id);
			return tipoCambioRepository.save(bean).map(usuario1 -> new ResponseEntity<>(usuario1, HttpStatus.ACCEPTED));
		}).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
