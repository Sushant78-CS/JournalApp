// package com.example.journalApp.service;

// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.mockito.Mockito.when;

// import java.lang.reflect.Array;
// import java.util.ArrayList;

// import org.assertj.core.util.Arrays;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.ArgumentMatcher;
// import org.mockito.ArgumentMatchers;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.security.core.userdetails.UserDetails;

// import org.springframework.security.core.userdetails.User;

// import com.example.journalApp.entity.UserEntry;
// import com.example.journalApp.repository.UserRepository;

// // @SpringBootTest
// public class UserDetailsServiceImplTest {

//     // @Autowired
//     @InjectMocks
//     private UserDetailsServiceImpl userDetailsServiceImpl;

//     // @MockBean
//     @Mock
//     private UserRepository userRepository;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.initMocks(this);
//     }

//     @Test
//     void loadUserByUserName() {
//         when(userRepository.findByUserName(ArgumentMatchers.anyString()))
//                 .thenReturn(UserEntry.builder().userName("Sumit").password("dfgvsdfcgv").roles(new ArrayList<>())
//                         .build());
//         UserDetails user = userDetailsServiceImpl.loadUserByUsername("Sumit");
//         assertNotNull(user);
//     }

// }
