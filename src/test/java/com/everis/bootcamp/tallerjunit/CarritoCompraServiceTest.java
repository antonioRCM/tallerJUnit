package com.everis.bootcamp.tallerjunit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarritoCompraServiceTest {

	private CarritoCompraService service;
	//private CarritoCompraService service2;
	
	BaseDeDatosService mock;
	
	@Before
	public void setUp() {
		System.out.println("Inicializamos el servicio");
		service = new CarritoCompraService();
		mock = Mockito.mock(BaseDeDatosService.class);
		service.setBbddService(mock);
		when(mock.findArticuloById(1)).thenReturn(new Articulo("Articulo 1",10d));

	}
	
	@Test
	public void primerTest() {
		System.out.println("Primer test");
	}
	
	@Test
	public void testaddArticulo() {
		System.out.println("Probando addArticulo");
		assertTrue(service.getArticulos().isEmpty());
		service.addArticulo(new Articulo("Articulo 1", 10d));
		assertFalse(service.getArticulos().isEmpty());
	}
	
	@Test
	public void testlimpiarCesta() {
		System.out.println("Probando limpiarCesta");
		assertTrue(service.getArticulos().isEmpty());
		service.addArticulo(new Articulo("Articulo 1", 10d));
		assertFalse(service.getArticulos().isEmpty());
		service.limpiarCesta();
		assertTrue(service.getArticulos().isEmpty());
	}
	
	@Test
	public void testtotalPrice() {
		System.out.println("Probando totalPrice");
		service.addArticulo(new Articulo("Articulo 1", 10d));
		service.addArticulo(new Articulo("Articulo 2", 20d));
		assertTrue(service.totalPrice() == 30d);
	}
	
	@Test
	public void testcalculadorDescuentos() {
		System.out.println("Probando CalculadorDescuentos");
		assertTrue(service.calculadorDescuento(10d, 20d) == 8d);
	}
	
	@Test
	public void testaplicarDescuento() {
		System.out.println("Probando AplicarDescuento");
		assertTrue(service.aplicarDescuento(1, 20d) == 8d);
	}
	
	@Test(expected = NullPointerException.class) //Con esto le dices la excepcion que esperas tener
	public void testaplicarDescuento2() {
		System.out.println("Probando AplicarDescuento2");
		when(mock.findArticuloById(0)).thenThrow(new NullPointerException()); //Le dices la excepcion que te tiene que dar
		assertTrue(service.aplicarDescuento(0, 20d) == 8d);
	}
}
