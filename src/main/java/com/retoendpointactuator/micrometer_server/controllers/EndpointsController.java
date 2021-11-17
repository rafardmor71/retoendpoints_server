package com.retoendpointactuator.micrometer_server.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Component
@Endpoint(id = "estados")
public class EndpointsController {
	private final static Logger logger = LoggerFactory.getLogger(EndpointsController.class);

	private Counter counter;
	
	public EndpointsController(MeterRegistry registry) {
		this.counter = Counter.builder("invocaciones.estados").description("Invocaciones totales").register(registry);
	}
	//private List<String> listado = ArrayList<>();
	private static  List<String> listado = Arrays.asList("open", "close", "half-open");

	
	@ReadOperation
	public List<String> estados(){
		counter.increment();
		return listado;
	}

	
	@WriteOperation
	public void writeOperationOpen(@Selector String nuevo) {
		counter.increment();
		listado.add(nuevo);
	}

	/*
	@DeleteOperation
	public void deleteOperation(@Selector String estadoBorrar) {
		listado.remove(estadoBorrar);
	}
	*/
	
}

	
