package vertex.pro.edu.soung_box_app.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(PublicUserController.class)
public class PublicUserControllerTest {
    @Autowired
    private MockMvc mvc;

//    @MockBean
//    private EmployeeService service;
}
