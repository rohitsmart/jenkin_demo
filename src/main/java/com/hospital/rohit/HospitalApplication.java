package com.hospital.rohit;

import com.hospital.rohit.entity.*;
import com.hospital.rohit.repository.UserCredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.hospital.rohit.login.LoginController.sdfErrorPOJO;

@SpringBootApplication
@RequiredArgsConstructor
@EnableTransactionManagement

public class HospitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);

		File file = new File("SecondError.txt");
		if(file.exists())
		{
			System.out.println("file created "+file.getAbsoluteFile());
		}
		else
		{
			System.out.println("file created wrong ");
		}
	}

}




@Component
@RequiredArgsConstructor
class InertAdminRecords implements CommandLineRunner {


	@Autowired
	private final UserCredentialsRepository userCredentialsRepository;

	@Override
	public void run(String... args) throws Exception {
		Employee employee = new Employee();

		employee.setFull_name("ROHIT KUMAR");
		employee.setContact_number("9837960625");
		employee.setDesignation("ADMIN");
		employee.setFather_name("VIRENDRA KUMAR");
		employee.setDate(sdfErrorPOJO.format(new Date()));
		employee.setDate_of_birth("1995/02/28");//yyyy/MM/dd HH:mm:ss

		Account account = new Account();
		account.setAccount_type("Salary-Account");
		account.setAccount_opening_cost("0");
		account.setCurrent_balance("500");

		account.setLast_login("");
		account.setAccount_number("784848596321547");

		List<Statement> statementList =  new ArrayList<>();

		Statement statement = new Statement();
		statement.setCredit(500);
		statement.setDebit(0);
		statement.setInitial_amount(0);
		statement.setFinal_amount(500);
		statement.setTransaction_date(sdfErrorPOJO.format(new Date()));
		statement.setTransaction_id("1234567890");
		statement.setTransaction_status("SUCCESS");
		statementList.add(statement);

		account.setStatement(statementList);


		List<Address> addressList = new ArrayList<>();

		Address address = new Address();
		address.setCountry("INDIA");
		address.setState("UTTAR PRADESH");
		address.setCity("MUZAFFARNAGAR");
		address.setZip_code("251001");

		AddressProof addressProof = new AddressProof();
		addressProof.setAddress_type("AADHAAR");
		addressProof.setAddress_number("695855241344");

		address.setAddressProof(addressProof);

		Address address1 = new Address();
		address1.setCountry("INDIA");
		address1.setState("UTTAR PRADESH");
		address1.setCity("GAZIABAAD");
		address1.setZip_code("221001");

		AddressProof addressProof1 = new AddressProof();
		addressProof1.setAddress_type("DRIVING LICENCE");
		addressProof1.setAddress_number("0123456789");

		address1.setAddressProof(addressProof1);

		addressList.add(address);
		addressList.add(address1);

		Identity identity  = new Identity();
		identity.setIdentity_type("VOTER-ID");
		identity.setIdentity_number("4567893210");


		PanCardProof panCardProof = new PanCardProof();
		panCardProof.setPancard_number("1234567890");

		employee.setAccount(account);// done
		employee.setAddress(addressList);
		employee.setIdentity(identity);
		employee.setPanCardProof(panCardProof);

		UserCredentials userCredentials = new UserCredentials();
		userCredentials.setEmployee(employee);
		userCredentials.setUsername("rohit");
		String password = new BCryptPasswordEncoder().encode("rohit");
		userCredentials.setPassword(password);

		try {
			boolean check = userCredentialsRepository.existsByUsernameAndPassword("rohit", "rohit");
			if (check == false) {
				userCredentialsRepository.save(userCredentials);
				System.out.println("Success fully created admin");
			} else {
				System.out.println("already have admin");
			}
		}
		catch (Exception e)
		{
			System.out.println("exception caught "+e.getLocalizedMessage());
		}



	}
}
