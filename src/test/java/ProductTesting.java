import com.javadevelop.converter.ProductConverter;
import com.javadevelop.converter.UserConverter;
import com.javadevelop.dto.ProductDTO;
import com.javadevelop.dto.RoleDTO;
import com.javadevelop.dto.UserDTO;
import com.javadevelop.entity.CategoryEntity;
import com.javadevelop.entity.ProductEntity;
import com.javadevelop.repository.CategoryRepository;
import com.javadevelop.repository.ProductRepository;
import com.javadevelop.repository.UserRepository;
import com.javadevelop.service.impl.ProductService;
import com.javadevelop.service.impl.UserService;
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
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.method.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
//@ContextConfiguration
//@DataJpaTest
//@SpringBootTest
public class ProductTesting {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductConverter productConverter;
    
    @Mock
    ProductRepository productRepository;

    @Mock
    CategoryRepository categoryRepository;


    private ProductDTO productDTO ;


    ProductEntity productEntity;
    CategoryEntity categoryEntity;

    @Before
    public void setup() {

        productDTO = new ProductDTO();
        productDTO.setCode("productCodeTesting");
        productDTO.setName("productNameTesting");
        productDTO.setDescription("productDesTesting");
        productDTO.setShortDescription("productShortDesTesting");
        productDTO.setCategoryCode("tp");

        categoryEntity = new CategoryEntity();
        categoryEntity.setCode("tp");
        categoryEntity.setName("thanh pham");
        productEntity = new ProductEntity();
        productEntity.setCode("productCodeTesting");
        productEntity.setName("productNameTesting");
        productEntity.setDescription("productDesTesting");
        productEntity.setShortDescription("productShortDesTesting");
        productEntity.setCategory(categoryEntity);

        MockitoAnnotations.initMocks(this);

    }

    @Test
    @Order(1)
    public void TestConverter(){

        Mockito.when(productConverter.toProductDTO(new ProductEntity())).thenReturn(productDTO);
        Assert.assertEquals("productCodeTesting",productDTO.getCode());
    }

    @Test
    @Order(2)
    public void TestSave(){

        Mockito.when(productConverter.toProductEntity(productDTO)).thenReturn(productEntity);
        Mockito.when(productConverter.toProductDTO(productEntity)).thenReturn(productDTO);
        Mockito.when(categoryRepository.findOneByCode(productDTO.getCategoryCode())).thenReturn(categoryEntity);
        Mockito.when(productRepository.save(productEntity)).thenReturn(productEntity);

        ProductDTO productDTO1 = new ProductDTO();
//        productDTO1.setCode("productCodeTesting");
//        productDTO1.setName("productNameTesting");
        Mockito.when(productService.save(productDTO)).thenReturn(productDTO1);
        Assert.assertEquals("productCodeTesting",productDTO1.getCode());
        Assert.assertEquals("productNameTesting",productDTO1.getName());
    }

    @Test
    @Order(3)
    public void TestCount(){
        Mockito.when(productRepository.count()).thenReturn(2L);
        Assert.assertEquals(2,productService.totalItem());
    }
}
