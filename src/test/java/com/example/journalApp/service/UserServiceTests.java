// package com.example.journalApp.service;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;

// import org.junit.jupiter.api.Disabled;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.params.ParameterizedTest;
// import org.junit.jupiter.params.provider.CsvSource;
// import org.junit.jupiter.params.provider.ValueSource;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// @SpringBootTest
// public class UserServiceTests {

//     @Autowired
//     UserService userService;

//     @Disabled
//     @Test
//     public void testAdd() {
//         // assertEquals(12, 4 + 8);
//         assertNotNull(userService.findByUserName("Sushant"));
//     }

//     @ParameterizedTest
//     @ValueSource(strings = {
//             "Sushant",
//             "Jituraj",
//             "Sumit"
//     })
//     public void testName(String name) {
//         assertNotNull(userService.findByUserName(name));
//     }

//     @Disabled
//     @ParameterizedTest
//     @CsvSource({
//             "1,2,3",
//             "4,8,12",
//             "6,12,22"
//     })
//     public void testPara(int a, int b, int expected) {
//         assertEquals(expected, a + b);
//     }

// }