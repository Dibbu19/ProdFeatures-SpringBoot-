package com.prod.features;

import com.prod.features.clients.EmployeeClient;
import com.prod.features.dto.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class ProductionFeaturesApplicationTests {

	@Autowired
	private EmployeeClient employeeClient;

	@Test
	void getAllEmployeeTest() {
		List<EmployeeDTO> employeeDTO = employeeClient.getAllEmployee();
		System.out.println(employeeDTO);
	}

	@Test
	void getEmployeeByIdTest() {
		EmployeeDTO employeeDTO = employeeClient.getEmployeeById(1L);
		System.out.println(employeeDTO);
	}

	@Test
	void createNewEmployeeTest() {
		EmployeeDTO data = new EmployeeDTO(null,"Name","abc@gmail.com",
				20,"ADMIN",5000.0, LocalDate.of(2025,2,1),true);
		EmployeeDTO employeeDTO = employeeClient.createNewEmployee(data);
		System.out.println(employeeDTO);
	}
}
