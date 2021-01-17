package com.venns;

import com.venns.service.UserService;
import com.venns.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroSpringbootApplicationTests {

	@Autowired
	UserServiceImpl userService;

	@Test
	void contextLoads() {
		System.out.println(userService.queryUserByName("venns"));
	}

}
