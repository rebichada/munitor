package com.munnitorbackend;

import com.munnitorbackend.Model.GanadoDatos;
import com.munnitorbackend.Model.User;
import com.munnitorbackend.Service.GanadoDatosServiceImplements;
import com.munnitorbackend.Service.GanadoServiceImplements;
import com.munnitorbackend.Service.UserServiceImplement;
import javafx.scene.input.DataFormat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.rmi.runtime.Log;

import javax.xml.crypto.Data;
import java.io.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SpringBootTest
class MunnitorbackendApplicationTests {

	//@Autowired
	//UserServiceImplement userServiceImplement;

	@Autowired
	GanadoServiceImplements ganadoServiceImplements;

	@Autowired
	GanadoDatosServiceImplements ganadoDatosServiceImplements;

	@Test
	void contextLoads() throws ParseException {

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
