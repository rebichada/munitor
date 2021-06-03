package com.munnitorbackend;

import com.munnitorbackend.Model.*;
import com.munnitorbackend.Service.*;
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

import static javafx.scene.input.KeyCode.L;

@SpringBootTest
class MunnitorbackendApplicationTests {

	@Autowired
	IUserService userService;

	@Autowired
	IGanadoService ganadoService;

	@Autowired
	IGanadoDatosService ganadoDatosService;

	@Autowired
	IDireccionService direccionService;

	@Autowired
	IEmpleadoService empleadoService;

	@Autowired
	ITamboService tamboService;

	@Autowired
	IVacunaService vacunaService;

	@Autowired
	IEmpresaService empresaService;

	@Autowired
	IVacunaEmpresaService vacunaEmpresaService;

	@Autowired
	ICaravanaService caravanaService;

	@Test
	void contextLoads() throws ParseException {
		//creo los usuarios

		/**
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
		userService.create(uJacinto);
		userService.create(uOmar);
		userService.create(uJorge);
		userService.create(uJaimito);
		userService.create(uPedro);
		//-----------------------------------------

		//creo las direcciones de los empleados
		Direccion dOmar= new Direccion(1L,"Buenos Aires","Junin","","Rivadavia",2,0,0,1L,1L,"6000");
		Direccion dJorge= new Direccion(2L,"Buenos Aires","Junin","","Hipolito Yrigoyen",500,0,0,1L,1L,"6000");
		Direccion dJaimito= new Direccion(3L,"Buenos Aires","Lincoln","Esquina con rejas negras","Massey",300,0,0,1L,2L,"6070");
		Direccion dJacinto= new Direccion(4L,"Buenos Aires","Lincoln","Porton rojo","Alem",1050,0,0,1L,2L,"6070");
		Direccion dPedro= new Direccion(5L,"Buenos Aires","Pergamino","Esquina con rejas negras","Bv. Colon",300,0,0,1L,3L,"2700");

		//creo las direcciones de los tambos y empresas
		Direccion dTambo1= new Direccion(6L,"Buenos Aires","Junin","","",0,-34.557962D, -60.957506D,1L,1L,"6000");
		Direccion dTambo2= new Direccion(7L,"Buenos Aires","Lincoln","","",0,-34.891035D, -61.617042D,1L,2L,"6070");
		Direccion dTambo3= new Direccion(8L,"Buenos Aires","Junin","","",0,-34.537954D, -60.928121D,1L,1L,"6000");
		Direccion dTambo4= new Direccion(9L,"Buenos Aires","Junin","","",0,-34.560496D, -60.986161D,1L,1L,"6000");
		Direccion dTambo5= new Direccion(10L,"Buenos Aires","Pergamino","","",0,-33.957208D, -60.578334D, 1L,3L,"2700");
		Direccion dTambo6= new Direccion(11L,"Buenos Aires","Chacabuco","","",0,-34.666082D, -60.495065D,1L,4L,"6740");
		Direccion dTambo7= new Direccion(12L,"Buenos Aires","Rojas","","",0,-34.002280D, -60.629991D,1L,5L,"2705");
		Direccion dTambo8= new Direccion(13L,"Buenos Aires","Vedia","","",0,-34.515650D, -61.550184D,1L,6L,"6030");

		//direcciones de las empresas
		Direccion dEmpresa1= new Direccion(14L,"Buenos Aires","Junin","","Av. San Martin",230,0,0,1L,1L,"6000");
		Direccion dEmpresa2= new Direccion(15L,"Buenos Aires","Lincoln","","Chacabuco",600,0,0,1L,1L,"6070");
		Direccion dEmpresa3= new Direccion(16L,"Buenos Aires","Pergamino","","Bv. Rocha",400,0,0,1L,1L,"2700");

		//guardo las direcciones Empleados
		direccionService.guardarDireccion(dOmar);
		direccionService.guardarDireccion(dJacinto);
		direccionService.guardarDireccion(dJaimito);
		direccionService.guardarDireccion(dJorge);
		direccionService.guardarDireccion(dPedro);
		//guardo las direcciones Tambos
		direccionService.guardarDireccion(dTambo1);
		direccionService.guardarDireccion(dTambo2);
		direccionService.guardarDireccion(dTambo3);
		direccionService.guardarDireccion(dTambo4);
		direccionService.guardarDireccion(dTambo5);
		direccionService.guardarDireccion(dTambo6);
		direccionService.guardarDireccion(dTambo7);
		direccionService.guardarDireccion(dTambo8);
		//guardo las direcciones Empresas
		direccionService.guardarDireccion(dEmpresa1);
		direccionService.guardarDireccion(dEmpresa2);
		direccionService.guardarDireccion(dEmpresa3);


		//creo las empresas
		Empresa empresa1 = new Empresa(1L,dEmpresa1,"Los Tambitos","30-569832-20","","");
		Empresa empresa2 = new Empresa(2L,dEmpresa1,"Los Tambitos","30-569832-20","","");
		Empresa empresa3 = new Empresa(3L,dEmpresa1,"Los Tambitos","30-569832-20","","");
		//guardo las empresas
		try {
			empresaService.guardar(empresa1);
			empresaService.guardar(empresa2);
			empresaService.guardar(empresa3);
		}catch (Exception e){

		}

		//creo los tambos con sus respectivas direcciones
		Tambo t1 = new Tambo(1L,dTambo1,empresa1,"La Tranquera","011-548663", "Quinto");
		Tambo t2 = new Tambo(2L,dTambo2,empresa1,"Las Orquideas","011-512513", "Quinto");
		Tambo t3 = new Tambo(3L,dTambo3,empresa1,"El Gaucho","011-689547", "Tercero");
		Tambo t4 = new Tambo(4L,dTambo4,empresa1,"Lo Vanney","011-5324569", "Primero");

		Tambo t5 = new Tambo(5L,dTambo5,empresa2,"Los Figliuolo","011-123567", "Sexto");
		Tambo t6 = new Tambo(6L,dTambo6,empresa2,"La Franquicia","011-101113", "Segundo");

		Tambo t7 = new Tambo(7L,dTambo7,empresa3,"Lo Zabala","011-987543", "Tercero");
		Tambo t8 = new Tambo(8L,dTambo8,empresa3,"Lo Rebich","011-123547", "Cuarto");
		//guardo los tambos
		tamboService.guardar(t1);
		tamboService.guardar(t2);
		tamboService.guardar(t3);
		tamboService.guardar(t4);
		tamboService.guardar(t5);
		tamboService.guardar(t6);
		tamboService.guardar(t7);
		tamboService.guardar(t8);

		//creo los empleados
		Empleado eOmar = new Empleado(1L,dOmar,uOmar,empresa1,"Omar","zapata",29306582,"236-4523695");
		Empleado eJacinto = new Empleado(2L,dJacinto,uJacinto,empresa1,"Jacinto","Rucula",31306582,"2355-513252");
		Empleado eJorge = new Empleado(3L,dJorge,uJorge,empresa2,"Jorge","Kay",39306582,"236-4222596");
		Empleado eJaimito = new Empleado(4L,dJaimito,uJaimito,empresa2,"Jaimito","Melo",28306582,"2355-523695");
		Empleado ePedro = new Empleado(5L,dPedro,uPedro,empresa3,"Pedro","Sanchez",35306582,"247-753695");
		//guardo los empleados
		try {
			empleadoService.guardar(eOmar);
			empleadoService.guardar(eJacinto);
			empleadoService.guardar(eJaimito);
			empleadoService.guardar(eJorge);
			empleadoService.guardar(ePedro);
		}catch (Exception e){
			throw new ParseException(e.getMessage(),e.getCause().hashCode());
		}



		//creo las vacunas
		Vacuna v1= new Vacuna(1L,"Brucelosis","1 vez en la vida, terneras de 3 a 8 meses de edad.","viva cepa 19.");
		Vacuna v2= new Vacuna(2L,"Carbunelo o Àntrax","Anualmente en primavera a todas las categorias mayores de 6 meses de vida.","Viva Cepa Stem.");
		Vacuna v3= new Vacuna(3L,"Diarrea neonatal","Vaquillonas preñadas, dos dosis previas al parto.","Inactivada con cepas regionales y/o referencia.");
		Vacuna v4= new Vacuna(4L,"Enfermedades clostridiales","Terneras/os a los 3,4 y 5 meses de vida.","Inactivada cepas regionales.");
		Vacuna v5= new Vacuna(5L,"Diarrea viral bovina","Dos dosis previo o postdestete, preservicio y refuerzos anuales..","Inactivada cepas referencia y regionales.");
		Vacuna v6= new Vacuna(6L,"Rinotraqueitis","Dos dosis previo o postdestete, preservicio y refuerzos anuales.","Inactivada cepas referencia y regionales.");
		Vacuna v7= new Vacuna(7L,"Campylobacteriosis","Dos dosis preservicio. Repetir similar tratamiento anualmente","Inactivada cepas regionales activas capsuladas y de referencia.");
		Vacuna v8= new Vacuna(8L,"IBR-BVD","Dos dosis predestete, repetir cada 6 meses hasta los 2 años.","Inactivada cepas de referencia y regionales");
		Vacuna v9= new Vacuna(9L,"Leptospirosis","Dos dosis preservicio, refuerzos anuales.","Inactivada cepas regionales.");

		//guardo las vacunas
		vacunaService.guardar(v1);
		vacunaService.guardar(v2);
		vacunaService.guardar(v3);
		vacunaService.guardar(v4);
		vacunaService.guardar(v5);
		vacunaService.guardar(v6);
		vacunaService.guardar(v7);
		vacunaService.guardar(v8);
		vacunaService.guardar(v9);

		//creo las vacunas de las empresas
		VacunaEmpresa v1e1 =  new VacunaEmpresa(1L,empresa1,v1,20);
		VacunaEmpresa v2e1 =  new VacunaEmpresa(2L,empresa1,v2,50);
		VacunaEmpresa v3e1 =  new VacunaEmpresa(3L,empresa1,v3,120);
		VacunaEmpresa v4e1 =  new VacunaEmpresa(4L,empresa1,v4,300);
		VacunaEmpresa v5e1 =  new VacunaEmpresa(5L,empresa1,v5,203);
		VacunaEmpresa v6e1 =  new VacunaEmpresa(6L,empresa1,v6,110);

		VacunaEmpresa v1e2 =  new VacunaEmpresa(7L,empresa2,v1,104);
		VacunaEmpresa v5e2 =  new VacunaEmpresa(8L,empresa2,v5,50);
		VacunaEmpresa v3e2 =  new VacunaEmpresa(9L,empresa2,v3,82);

		VacunaEmpresa v2e3 =  new VacunaEmpresa(10L,empresa3,v2,5);
		VacunaEmpresa v3e3 =  new VacunaEmpresa(11L,empresa3,v3,21);
		VacunaEmpresa v7e3 =  new VacunaEmpresa(12L,empresa3,v7,60);
		VacunaEmpresa v8e3 =  new VacunaEmpresa(13L,empresa3,v8,26);
		VacunaEmpresa v9e3 =  new VacunaEmpresa(14L,empresa3,v9,15);

		//guardo las vacunas de las empresas
		vacunaEmpresaService.guardar(v1e1);
		vacunaEmpresaService.guardar(v2e1);
		vacunaEmpresaService.guardar(v3e1);
		vacunaEmpresaService.guardar(v4e1);
		vacunaEmpresaService.guardar(v5e1);
		vacunaEmpresaService.guardar(v6e1);

		vacunaEmpresaService.guardar(v1e2);
		vacunaEmpresaService.guardar(v5e2);
		vacunaEmpresaService.guardar(v3e2);

		vacunaEmpresaService.guardar(v2e3);
		vacunaEmpresaService.guardar(v3e3);
		vacunaEmpresaService.guardar(v7e3);
		vacunaEmpresaService.guardar(v8e3);
		vacunaEmpresaService.guardar(v9e3);


		//GUARDO LAS CARAVANADAS

		//creo las caravanas
		Caravana c1 = new Caravana(1L,empresa1,"AR","AJ 911",'A','2',"D591","00.123.0.0031/18","",new Date(),"00325","A145A232");
		Caravana c2 = new Caravana(2L,empresa1,"AR","AJ 340",'A','1',"A302","00.568.0.0359/58","",new Date(),"00156","A401A302");
		Caravana c3 = new Caravana(3L,empresa1,"AR","AJ 480",'A','1',"A407","00.512.0.0091/57","",new Date(2006,5,20),"00120","A403A301");
		Caravana c4 = new Caravana(4L,empresa1,"AR","JA 100",'A','3',"C215","00.912.0.0618/58","",new Date(2007,7,21),"00115","A441A322");
		Caravana c5 = new Caravana(5L,empresa1,"AR","JC 123",'V','5',"B342","00.852.0.0592/56","", new Date(2020,3,27), "00671", "A501A102");
		Caravana c6 = new Caravana(6L,empresa1,"AR","JC 152",'R','6',"B243","00.952.0.7453/55","",new Date(2015,11,12),"00421","A451A212");
		Caravana c7 = new Caravana(7L,empresa1,"AR","AZ 203",'R','8',"B314","00.392.0.0591/21","",new Date(2015,1,4),"00124","A771A442");

		Caravana c8 = new Caravana(8L,empresa2,"AR","AZ 312",'V','7',"A225","00.152.0.03469/52","",new Date(2015,11,12),"00231","A111A322");
		Caravana c9 = new Caravana(9L,empresa2,"AR","AZ 313",'V','7',"A815","00.131.0.02812/32","",new Date(2015,11,12),"00255","A861A352");
		Caravana c10 = new Caravana(10L,empresa2	,"AR","BD 442",'V','4',"A265","00.492.0.05215/77","",new Date(2011,10,15),"00240","A861A502");
		Caravana c11 = new Caravana(11L,empresa2,"AR","BS 223",'R','3',"A721","00.994.0.05259/68","",new Date(2012,11,15),"00320","A751A772");
		Caravana c12 = new Caravana(12L,empresa2	,"AR","BT 412",'R','9',"A822","00.744.0.05259/89","",new Date(2013,9,15),"00420","A466A777");
		Caravana c13 = new Caravana(13L,empresa2,"AR","BR 301",'A','8',"A900","00.545.0.05259/74","",new Date(2015,11,17),"00149","A101A202");
		Caravana c14 = new Caravana(14L,empresa2,"AR","ST 201",'A','6',"A420","00.623.0.01251/12","",new Date(2015,11,17),"00221","A606A303");

		Caravana c15 = new Caravana(15L,empresa3,"AR","AS 351",'A','5',"A321","00.054.0.04579/23","",new Date(2003,4,20),"00231","A551A999");
		Caravana c16 = new Caravana(16L,empresa3,"AR","PD 551",'A','4',"A111","00.945.0.08449/44","",new Date(2003,4,20),"00672","A491A382");
		Caravana c17 = new Caravana(17L,empresa3,"AR","GA 691",'A','3',"A221","00.645.0.60232/21","",new Date(2004,5,30),"00339","A451A606");
		Caravana c18 = new Caravana(18L,empresa3,"AR","JS 195",'R','2',"A345","00.512.0.0O012/22","",new Date(2004,5,30),"00402","A986A322");
		Caravana c19 = new Caravana(18L,empresa3,"AR","PQ 510",'R','1',"A335","00.433.0.07343/22","",new Date(2005,11,12),"00856","A346A457");
		Caravana c20 = new Caravana(20L,empresa3,"AR","HJ 601",'V','8',"A336","00.553.0.05512/32","",new Date(2005,11,12),"00888","A876A457");

		//creo la fecha para cada caravana
		String fc1 = "05-05-2017";
		String fc2 = "02-04-2018";
		String fc3 = "11-07-2020";
		String fc4 = "25-10-2020";
		String fc5 = "17-12-2020";
		String fc6 = "20-01-2021";
		String fc7 = "05-02-2021";
		String fc8 = "21-07-2018";
		String fc9 = "27-11-2019";
		String fc10 = "17-12-2019";
		String fc11 = "20-03-2019";
		String fc12 = "02-05-2019";
		String fc13 = "21-05-2019";
		String fc14 = "01-10-2019";
		String fc15 = "17-12-2019";
		String fc16 = "20-11-2019";
		String fc17 = "02-04-2018";
		String fc18 = "30-07-2016";
		String fc19 = "25-10-2017";
		String fc20 = "16-12-2021";

		try{
			//seteo la fecha correspondiente con el formato correspondiente para cada caravana
			c1.setFechaImpresion(dataFormat.parse(fc1));
			c2.setFechaImpresion(dataFormat.parse(fc2));
			c3.setFechaImpresion(dataFormat.parse(fc3));
			c4.setFechaImpresion(dataFormat.parse(fc4));
			c5.setFechaImpresion(dataFormat.parse(fc5));
			c6.setFechaImpresion(dataFormat.parse(fc6));
			c7.setFechaImpresion(dataFormat.parse(fc7));
			c8.setFechaImpresion(dataFormat.parse(fc8));
			c9.setFechaImpresion(dataFormat.parse(fc9));
			c10.setFechaImpresion(dataFormat.parse(fc10));
			c11.setFechaImpresion(dataFormat.parse(fc11));
			c12.setFechaImpresion(dataFormat.parse(fc12));
			c13.setFechaImpresion(dataFormat.parse(fc13));
			c14.setFechaImpresion(dataFormat.parse(fc14));
			c15.setFechaImpresion(dataFormat.parse(fc15));
			c16.setFechaImpresion(dataFormat.parse(fc16));
			c17.setFechaImpresion(dataFormat.parse(fc17));
			c18.setFechaImpresion(dataFormat.parse(fc18));
			c19.setFechaImpresion(dataFormat.parse(fc19));
			c20.setFechaImpresion(dataFormat.parse(fc20));

			//guardo todas las caravanas
			caravanaService.guardar(c1);
			caravanaService.guardar(c2);
			caravanaService.guardar(c3);
			caravanaService.guardar(c4);
			caravanaService.guardar(c5);
			caravanaService.guardar(c6);
			caravanaService.guardar(c7);
			caravanaService.guardar(c8);
			caravanaService.guardar(c9);
			caravanaService.guardar(c10);
			caravanaService.guardar(c11);
			caravanaService.guardar(c12);
			caravanaService.guardar(c13);
			caravanaService.guardar(c14);
			caravanaService.guardar(c15);
			caravanaService.guardar(c16);
			caravanaService.guardar(c17);
			caravanaService.guardar(c18);
			caravanaService.guardar(c19);
			caravanaService.guardar(c20);

		}catch (Exception e){

		}


		//creo los ganados
		Ganado g1 = new Ganado(1L,c1,t1,0,'M',"toro holando",new Date());
		Ganado g2 = new Ganado(2L,c2,t1,2,'F',"vaca holanda",new Date());
		Ganado g3 = new Ganado(3L,c3,t2,1,'F',"vaca holanda",new Date());
		Ganado g4 = new Ganado(4L,c4	,t2,0,'M',"toro holando",new Date());
		Ganado g5 = new Ganado(5L,c5,t3,0,'F',"vaca holanda",new Date());
		Ganado g6 = new Ganado(6L,c6	,t4,0,'F',"vaca holanda",new Date());
		Ganado g7 = new Ganado(7L,c7,t4,1,'F',"vaca holanda",new Date());

		Ganado g8 = new Ganado(8L,c8,t5,1,'F',"vaca holanda",new Date());
		Ganado g9 = new Ganado(9L,c9,t5,1,'F',"vaca holanda",new Date());
		Ganado g10 = new Ganado(10L,c10,t5,1,'F',"vaca holanda",new Date());
		Ganado g11 = new Ganado(11L,c11,t5,0,'M',"toro holando",new Date());
		Ganado g12 = new Ganado(12L,c12,t6,3,'F',"vaca holanda",new Date());
		Ganado g13 = new Ganado(13L,c13,t6,1,'F',"vaca holanda",new Date());
		Ganado g14 = new Ganado(14L,c14,t6,2,'F',"vaca holanda",new Date());

		Ganado g15 = new Ganado(15L,c15,t7,1,'F',"vaca holanda",new Date());
		Ganado g16 = new Ganado(16L,c16,t7,1,'F',"vaca holanda",new Date());
		Ganado g17 = new Ganado(17L,c17,t7,2,'F',"vaca holanda",new Date());
		Ganado g18 = new Ganado(18L,c18,t7,0,'F',"vaca holanda",new Date());
		Ganado g19 = new Ganado(19L,c19,t8,0,'F',"vaca holanda",new Date());
		Ganado g20 = new Ganado(20L,c20,t8,0,'M',"toro holando",new Date());

		String fg1 = "05-05-2021";
		String fg2 = "02-04-2020";
		String fg3 = "11-07-2021";
		String fg4 = "25-10-2020";
		String fg5 = "17-12-2020";
		String fg6 = "20-01-2021";
		String fg7 = "05-02-2021";
		String fg8 = "21-07-2020";
		String fg9 = "27-11-2019";
		String fg10 = "17-12-2019";
		String fg11 = "20-03-2019";
		String fg12 = "02-05-2019";
		String fg13 = "21-05-2019";
		String fg14 = "01-10-20setFechaDeNacimiento(dataFormat.parse(fg14));
		 g15.setFechaDeNacimiento(dataFormat.parse(fg15));
		 g16.setFechaDeNacimiento(dataFormat.parse(fg16));
		 g17.setFechaDeNacimiento(dataFormat.parse(fg17));
		 g18.setFechaDeNacimiento(dataFormat.parse(fg18));
		 g19.setFechaDeNacimiento(dataFormat.parse(fg19));
		 g20.setFechaDeNacimiento(dataFormat.parse(fg20));

		 }catch (Exception19";
		String fg15 = "17-12-2019";
		String fg16 = "20-11-2019";
		String fg17 = "02-04-2021";
		String fg18 = "30-07-2019";
		String fg19 = "25-10-2020";
		String fg20 = "16-12-2021";

		try{
			g1.setFechaDeNacimiento(dataFormat.parse(fg1));
			g2.setFechaDeNacimiento(dataFormat.parse(fg2));
			g3.setFechaDeNacimiento(dataFormat.parse(fg3));
			g4.setFechaDeNacimiento(dataFormat.parse(fg4));
			g5.setFechaDeNacimiento(dataFormat.parse(fg5));
			g6.setFechaDeNacimiento(dataFormat.parse(fg6));
			g7.setFechaDeNacimiento(dataFormat.parse(fg7));
			g8.setFechaDeNacimiento(dataFormat.parse(fg8));
			g9.setFechaDeNacimiento(dataFormat.parse(fg9));
			g10.setFechaDeNacimiento(dataFormat.parse(fg10));
			g11.setFechaDeNacimiento(dataFormat.parse(fg11));
			g12.setFechaDeNacimiento(dataFormat.parse(fg12));
			g13.setFechaDeNacimiento(dataFormat.parse(fg13));
			g14. e){

		}

		ArrayList<Ganado> ganados = new ArrayList<>();
		ganados.add(g1);
		ganados.add(g2);
		ganados.add(g3);
		ganados.add(g4);
		ganados.add(g5);
		ganados.add(g6);
		ganados.add(g7);
		ganados.add(g8);
		ganados.add(g9);
		ganados.add(g10);
		ganados.add(g11);
		ganados.add(g12);
		ganados.add(g13);
		ganados.add(g14);
		ganados.add(g15);
		ganados.add(g16);
		ganados.add(g17);
		ganados.add(g18);
		ganados.add(g19);
		ganados.add(g20);


		for (Ganado g:ganados) {
			try{
				ganadoService.guardar(g);
			} catch (Exception e){

			}
		}
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
