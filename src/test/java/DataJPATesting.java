import com.javadevelop.entity.RoleEntity;
import com.javadevelop.entity.UserEntity;
import com.javadevelop.repository.UserRepository;
import org.assertj.core.api.Assertions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataJPATesting {

    @Mock
    private DataSource dataSource;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private EntityManager entityManager;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setup() {
//        RoleDTO roleDTO = new RoleDTO();
//        roleDTO.setCode("USER");
//        roleDTO.setName("user");
//        List<RoleDTO> roleDTOs = new ArrayList<>();
//        roleDTOs.add(roleDTO);
//
//        userDTO = new UserDTO();
//        userDTO.setUserName("userTesting");
//        userDTO.setPassword("userTesting");
//        userDTO.setFullName("userTesting");
//        userDTO.setStatus(1);
//        userDTO.setRoles(roleDTOs);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void allComponentAreNotNull() {
        Assertions.assertThat(dataSource).isNotNull();
        Assertions.assertThat(jdbcTemplate).isNotNull();
        Assertions.assertThat(entityManager).isNotNull();
        Assertions.assertThat(userRepository).isNotNull();
    }

    @Test
    @Rollback(value = false)
    public void TestSave() {

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setCode("USER");
        roleEntity.setName("user");
        List<RoleEntity> roleEntitys = new ArrayList<>();
        roleEntitys.add(roleEntity);

        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("userTesting");
        userEntity.setPassword("userTesting");
        userEntity.setFullName("userTesting");
        userEntity.setStatus(1);
        userEntity.setRoles(roleEntitys);

//        entityManager.persist(userEntity);
//        entityManager.flush();

        UserEntity userEntity1 = userRepository.save(userEntity);

        Assert.assertNotNull(userEntity1);

//        Assert.assertEquals(userEntity1.getUserName(),userEntity.getUserName());

//        Assertions.assertThat(userRepository.findAll()).hasSize(1);
    }
}
