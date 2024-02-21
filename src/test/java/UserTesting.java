import com.javadevelop.converter.UserConverter;
import com.javadevelop.dto.RoleDTO;
import com.javadevelop.dto.UserDTO;
import com.javadevelop.entity.RoleEntity;
import com.javadevelop.entity.UserEntity;
import com.javadevelop.repository.RoleRepository;
import com.javadevelop.repository.UserRepository;
import com.javadevelop.service.IUserService;
import com.javadevelop.service.impl.UserService;
import org.apache.catalina.User;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserTesting {

    @InjectMocks
    UserService userService;

    @Mock
    UserConverter userConverter;

    @Mock
    RoleRepository roleRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    UserRepository userRepository;

    private UserDTO userDTO;
    private UserEntity userEntity;

    @Before
    public void setup() {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setCode("USER");
        roleDTO.setName("user");
        List<RoleDTO> roleDTOs = new ArrayList<>();
        roleDTOs.add(roleDTO);

        userDTO = new UserDTO();
        userDTO.setUserName("userTesting");
        userDTO.setPassword("userTesting");
        userDTO.setFullName("userTesting");
        userDTO.setStatus(1);
        userDTO.setRoles(roleDTOs);

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setCode("USER");
        roleEntity.setName("user");
        List<RoleEntity> roleEntitys = new ArrayList<>();
        roleEntitys.add(roleEntity);

        userEntity = new UserEntity();
        userEntity.setUserName("userTesting");
        userEntity.setPassword("userTesting");
        userEntity.setFullName("userTesting");
        userEntity.setStatus(1);
        userEntity.setRoles(roleEntitys);

        MockitoAnnotations.initMocks(this);

    }

    @Test
    @Order(1)
    public void TestSave() {

        Mockito.when(userConverter.toUserEntity(userDTO)).thenReturn(userEntity);
        Mockito.when(userRepository.save(userEntity)).thenReturn(userEntity);
        Mockito.when(userConverter.toUserDTO(userEntity)).thenReturn(userDTO);

        UserDTO userDTOsave = userService.save(userDTO);
        Assert.assertEquals("userTesting", userDTOsave.getUserName());
    }

    @Test
    @Order(3)
    public void TestCount() {
        Mockito.when(userRepository.count()).thenReturn(1L);
        Assert.assertEquals(1, userService.totalItem());
    }

    @Test
    @Order(4)
    public void TestConverterToEntity() {
        Mockito.when(userConverter.toUserEntity(userDTO)).thenReturn(userEntity);
        UserEntity userEntity1 = userConverter.toUserEntity(userDTO);
        Assert.assertEquals("userTesting", userEntity1.getFullName());
    }

    @Test
    @Order(5)
    public void TestConverterToDTO() {
        Mockito.when(userConverter.toUserDTO(userEntity)).thenReturn(userDTO);
        UserDTO userDTO1 = userConverter.toUserDTO(userEntity);
        Assert.assertEquals("userTesting", userDTO1.getFullName());
    }

    @Test
    @Order(6)
    public void TestSaveRepository() {
        Mockito.when(userRepository.save(new UserEntity())).thenReturn(userEntity);
        Assert.assertEquals("userTesting", userEntity.getFullName());
    }

    @Test
    @Order(8)
    public void TestDeleteRepository2() {
        userService = mock(UserService.class);
        UserDTO userDTO1 = new UserDTO();
        userDTO1.setId(1L);

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setId(1L);

        long[] listId1 = {userDTO1.getId()};
        long[] listId2 = {userDTO2.getId()};

        Mockito.when(userService.findOneById(userDTO2.getId())).thenReturn(userDTO2);
        userService.delete(listId1);
        Mockito.verify(userService, times(1)).delete(listId2); // xác minh phương thức có được gọi (nếu không thì fail)
    }
}
