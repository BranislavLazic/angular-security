package org.angularsecurity;

import java.util.Arrays;

import javax.servlet.Filter;

import org.angularsecurity.domain.Employee;
import org.angularsecurity.domain.User;
import org.angularsecurity.domain.UserRole;
import org.angularsecurity.repository.EmployeeRepository;
import org.angularsecurity.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public InitializingBean insertDefaultUsers() {
		return new InitializingBean() {
			@Autowired
			private UserRepository userRepository;

			@Autowired
			private EmployeeRepository employeeRepository;

			@Override
			public void afterPropertiesSet() {
				// Add default users
				addUser("admin", "admin");
				addUser("user", "user");
				// Add some employees
				employeeRepository.save(Arrays.asList(new Employee("John", "Doe", 23), new Employee("Jane", "Doe", 23)));
			}

			private void addUser(String username, String password) {
				User user = new User();
				user.setUsername(username);
				user.setPassword(new BCryptPasswordEncoder().encode(password));
				user.grantRole(username.equals("admin") ? UserRole.ADMIN : UserRole.USER);
				userRepository.save(user);
			}
		};
	}

	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}

}
