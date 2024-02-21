import com.javadevelop.controller.UserController;
import com.javadevelop.dto.BaseDTO;
import com.javadevelop.dto.UserDTO;
import com.javadevelop.repository.UserRepository;
import com.javadevelop.service.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class ControllerTesting {

    @InjectMocks
    UserController userController;

    @Mock
    IUserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    BaseDTO baseDTO;

    private List<UserDTO> allUserDTO;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();

        allUserDTO = new ArrayList<>();
        for (long i = 1; i<=10;i++) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(i);
            userDTO.setUserName("userName " + i);
            userDTO.setFullName("userFullName " + i);
            allUserDTO.add(userDTO);
        }

    }

    @Test
    public void testGETController() throws Exception {

        this.mockMvc.perform(get("/user").contentType(MediaType.APPLICATION_JSON)) // Thực hiện GET REQUEST
                .andExpect(status().isOk()); // Mong muốn Server trả về status 200
    }

    @Test
    public void testPOSTController() throws Exception {

        String stringJson = "{\"userName\":\"usertest\",\"password\":\"usertest\",\"fullName\":\"usertest\",\"status\":\"2\",\"roles\":[{\"code\":\"USER\"}]}";
        this.mockMvc.perform(post("/user")
                .accept(MediaType.APPLICATION_JSON)
                .content(stringJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); // Mong muốn Server trả về status 200
    }

    @Test
    public void testPUTController() throws Exception {

        String stringJson = "{\"userName\":\"usertest\",\"password\":\"usertest\",\"fullName\":\"usertest\",\"status\":\"2\",\"roles\":[{\"code\":\"USER\"}]}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/user/1")
                .accept(MediaType.APPLICATION_JSON)
                .content(stringJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

    @Test
    public void testDELETEController() throws Exception {

        this.mockMvc.perform(delete("/user")
                .accept(MediaType.APPLICATION_JSON)
                .content("[14]")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) ;// Mong muốn Server trả về status 200
    }
}
