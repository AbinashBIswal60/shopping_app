package com.cleancode.shopping;

import com.cleancode.shopping.entity.User;
import com.cleancode.shopping.repository.UserRepostory;
import com.cleancode.shopping.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//@Runwith(SpringRunner.class)
@SpringBootTest
class ShoppingApplicationTests {

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepostory userRepostory;

	@Test
	void contextLoads() {
	}

	@Test
	public void getUserByIdTest(){
		when(userRepostory.findById(12L)).thenReturn(Optional.of(new User(12, "abc", 1234567890)));

		assertEquals(new User(12, "abc", 1234567890), userService.getUserById(12L));
	}
}
