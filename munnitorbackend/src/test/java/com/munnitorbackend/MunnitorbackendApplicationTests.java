package com.munnitorbackend;

import com.munnitorbackend.Model.*;
import com.munnitorbackend.Service.GanadoDatosServiceImplements;
import com.munnitorbackend.Service.GanadoServiceImplements;
import com.munnitorbackend.Service.UserServiceImplement;
import com.sun.istack.NotNull;
import javafx.scene.input.DataFormat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.rmi.runtime.Log;

import javax.persistence.Column;
import javax.xml.crypto.Data;
import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SpringBootTest
class MunnitorbackendApplicationTests {

	@Autowired
	UserServiceImplement userServiceImplement;

	@Autowired
	GanadoServiceImplements ganadoServiceImplements;

	@Autowired
	GanadoDatosServiceImplements ganadoDatosServiceImplements;

	@Test
	void contextLoads() throws ParseException {
		//creo los usuarios
		SimpleDateFormat dataFormat=  new SimpleDateFormat("dd-MM-yyyy");

		User uPedro= new User("pedro_zanchez@hotmail.com","pedro","sanchez","123",new Date(),"argentina");
		User uOmar= new User("omar_zapata@hotmail.com","omar","zapata","123",new Date(),"colombia");
		User uJacinto= new User("jacinto_rucula@hotmail.com","jacinto","rucula","123",new Date(),"uruguay");
		User uJorge= new User("jorge_kay@hotmail.com","jorge","kay","123",new Date(),"brasil");
		User uJaimito= new User("jaimito_melo@hotmail.com","jaimito","melo","123",new Date(),"argentina");

		String fechaNacimientoPedro = "20-11-1980";
		String fechaNacimientoJacinto = "02-04-1988";
		String fechaNacimientoJaimito = "21-07-1990";
		String fechaNacimientoOmar = "25-10-1996";
		String fechaNacimientoJorge = "17-12-1976";
		//seteo la fecha de nacimiento de cada uno

		try {
			uOmar.setBirthDate(dataFormat.parse(fechaNacimientoOmar));
			uJacinto.setBirthDate(dataFormat.parse(fechaNacimientoJacinto));
			uJorge.setBirthDate(dataFormat.parse(fechaNacimientoJorge));
			uJaimito.setBirthDate(dataFormat.parse(fechaNacimientoJaimito));
			uPedro.setBirthDate(dataFormat.parse(fechaNacimientoPedro));
		}catch (Exception e){
			throw new ParseException(e.getMessage(),e.getCause().hashCode());
		}
		//guardo los usuarios en la bd
		userServiceImplement.create(uJacinto);
		userServiceImplement.create(uOmar);
		userServiceImplement.create(uJorge);
		userServiceImplement.create(uJaimito);
		userServiceImplement.create(uPedro);
		//-----------------------------------------

		//creo las direcciones de los empleados
		Direccion dOmar= new Direccion(1L,"Buenos Aires","Junin","","Rivadavia",2,0,0,1L,1L,"6000");
		Direccion dJorge= new Direccion(2L,"Buenos Aires","Junin","","Hipolito Yrigoyen",500,0,0,1L,1L,"6000");
		Direccion dJaimito= new Direccion(3L,"Buenos Aires","Lincoln","Esquina con rejas negras","Massey",300,0,0,1L,2L,"6070");
		Direccion dJacinto= new Direccion(4L,"Buenos Aires","Lincoln","Porton rojo","Alem",1050,0,0,1L,2L,"6070");
		Direccion dPedro= new Direccion(5L,"Buenos Aires","Pergamino","Esquina con rejas negras","Bv. Colon",300,0,0,1L,3L,"2700");

		//creo los empleados
		Empleado eOmar = new Empleado();








		Vacuna v1= new Vacuna(1L,"Brucelosis","1 vez en la vida, terneras de 3 a 8 meses de edad.","viva cepa 19.",3);
		Vacuna v2= new Vacuna(2L,"Carbunelo o Àntrax","Anualmente en primavera a todas las categorias mayores de 6 meses de vida.","Viva Cepa Stem.",2);
		Vacuna v3= new Vacuna(3L,"Diarrea neonatal","Vaquillonas preñadas, dos dosis previas al parto.","Inactivada con cepas regionales y/o referencia.",4);
		Vacuna v4= new Vacuna(4L,"Enfermedades clostridiales","Terneras/os a los 3,4 y 5 meses de vida.","Inactivada cepas regionales.",10);
		Vacuna v5= new Vacuna(5L,"Diarrea viral bovina","Dos dosis previo o postdestete, preservicio y refuerzos anuales..","Inactivada cepas referencia y regionales.",2);
		Vacuna v6= new Vacuna(6L,"Rinotraqueitis","Dos dosis previo o postdestete, preservicio y refuerzos anuales.","Inactivada cepas referencia y regionales.",1);
		Vacuna v7= new Vacuna(7L,"Campylobacteriosis","Dos dosis preservicio. Repetir similar tratamiento anualmente","Inactivada cepas regionales activas capsuladas y de referencia.",7);
		Vacuna v8= new Vacuna(8L,"IBR-BVD","Dos dosis predestete, repetir cada 6 meses hasta los 2 años.","Inactivada cepas de referencia y regionales",7);
		Vacuna v9= new Vacuna(5L,"Leptospirosis","Dos dosis preservicio, refuerzos anuales.","Inactivada cepas regionales.",2);



		/**private User user;

		@Column(name = "nombre", length = 40, nullable = false)
		private String nombre;

		@Column(name = "apellido", length =40,nullable = false)
		private String apellido;

		@Column(name = "dni", length = 15,nullable = false)
		private int dni;

		@Column(length = 20)
		private String telefono;**/

		/**u.setEmail("user@hotmail.com");
		u.setId(1L);
		u.setPassword("user");
		u.setLastName("user");
		u.setFirstName("user");
		u.setNationality("Argentina");
		String fechaActual = "20-11-2020";
		try {
			SimpleDateFormat dataFormat=  new SimpleDateFormat("dd-MM-yyyy");
			u.setBirthDate(dataFormat.parse(fechaActual));
		}catch (Exception e){
			throw new ParseException(e.getMessage(),e.getCause().hashCode());
		}


		userServiceImplement.create(u);**/



	}




}
