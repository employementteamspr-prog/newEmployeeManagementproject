package com.examly.springapp;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.lang.annotation.Annotation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    // ===================== DAY 1 - Directory Checks =====================
    @Test @Order(1)
    void testEmployeeDirectoryExists() {
        assertTrue(new File("src/main/java/com/examly/springapp/employee").isDirectory(),
                   "Employee directory should exist");
    }

    @Test @Order(2)
    void testControllerDirectoryExists() {
        assertTrue(new File("src/main/java/com/examly/springapp/controller").isDirectory(),
                   "Controller directory should exist");
    }

    @Test @Order(3)
    void testServiceDirectoryExists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service").isDirectory(),
                   "Service directory should exist");
    }

    @Test @Order(4)
    void testRepositoryDirectoryExists() {
        assertTrue(new File("src/main/java/com/examly/springapp/repository").isDirectory(),
                   "Repository directory should exist");
    }

    @Test @Order(5)
    void testExceptionDirectoryExists() {
        assertTrue(new File("src/main/java/com/examly/springapp/exception").isDirectory(),
                   "Exception directory should exist");
    }

    // ===================== DAY 2 - Model File Checks =====================
    @Test @Order(6)
    void testEmployeeModelFileExists() {
        assertTrue(new File("src/main/java/com/examly/springapp/employee/Employee.java").isFile(),
                   "Employee.java file should exist");
    }

    @Test @Order(7)
    void testEmployeeHasEntityAnnotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.employee.Employee");
            Class<?> annotation = Class.forName("jakarta.persistence.Entity");
            assertTrue(clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
                       "@Entity annotation is missing on Employee class");
        } catch (Exception e) {
            fail("Unable to check @Entity annotation on Employee.");
        }
    }

    @Test @Order(8)
    void testEmployeeHasIdAnnotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.employee.Employee");
            Class<?> idAnnotation = Class.forName("jakarta.persistence.Id");
            boolean found = false;
            for (var field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent((Class<? extends Annotation>) idAnnotation)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found, "No field in Employee class is annotated with @Id");
        } catch (Exception e) {
            fail("Unable to verify @Id annotation in Employee class.");
        }
    }

    // ===================== DAY 3 - Repository Checks =====================
    @Test @Order(9)
    void testEmployeeRepositoryFileExists() {
        assertTrue(new File("src/main/java/com/examly/springapp/repository/EmployeeRepository.java").isFile(),
                   "EmployeeRepository.java file should exist");
    }

    @Test @Order(10)
    void testEmployeeRepositoryHasAnnotation() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.repository.EmployeeRepository");
            Class<?> annotation = Class.forName("org.springframework.stereotype.Repository");
            assertTrue(clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
                       "@Repository annotation is missing on EmployeeRepository class");
        } catch (Exception e) {
            fail("Unable to verify @Repository annotation on EmployeeRepository.");
        }
    }

    // ===================== DAY 4 - Service Checks =====================
    @Test @Order(11)
    void testEmployeeServiceFileExists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/EmployeeService.java").isFile(),
                   "EmployeeService.java file should exist");
    }

    @Test @Order(12)
    void testEmployeeServiceImplFileExists() {
        assertTrue(new File("src/main/java/com/examly/springapp/service/impl/EmployeeServiceImpl.java").isFile(),
                   "EmployeeServiceImpl.java file should exist");
    }

    // ===================== DAY 5 - Controller Checks =====================
    @Test @Order(13)
    void testEmployeeControllerFileExists() {
        assertTrue(new File("src/main/java/com/examly/springapp/controller/EmployeeController.java").isFile(),
                   "EmployeeController.java file should exist");
    }

    @Test @Order(14)
    void testEmployeeControllerHasRestController() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.EmployeeController");
            Class<?> annotation = Class.forName("org.springframework.web.bind.annotation.RestController");
            assertTrue(clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
                       "@RestController annotation is missing on EmployeeController class");
        } catch (Exception e) {
            fail("Unable to verify @RestController annotation on EmployeeController.");
        }
    }

    @Test @Order(15)
    void testEmployeeControllerHasRequestMapping() {
        try {
            Class<?> clazz = Class.forName("com.examly.springapp.controller.EmployeeController");
            Class<?> annotation = Class.forName("org.springframework.web.bind.annotation.RequestMapping");
            assertTrue(clazz.isAnnotationPresent((Class<? extends Annotation>) annotation),
                       "@RequestMapping annotation is missing on EmployeeController class");
        } catch (Exception e) {
            fail("Unable to verify @RequestMapping annotation on EmployeeController.");
        }
    }

    // ===================== DAY 6 - MockMvc CRUD Tests (Optional) =====================
    @Test @Order(16)
    public void testAddEmployeeReturnsBadRequestWithoutBody() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
