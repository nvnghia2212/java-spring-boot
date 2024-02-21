import com.javadevelop.converter.ProductConverter;
import com.javadevelop.dto.ProductDTO;
import com.javadevelop.entity.CategoryEntity;
import com.javadevelop.entity.ProductEntity;
import com.javadevelop.repository.CategoryRepository;
import com.javadevelop.repository.ProductRepository;
import com.javadevelop.service.impl.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
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

        Mockito.when(productConverter.toProductDTO(productEntity)).thenReturn(productDTO);
        ProductDTO productDTO1 = productConverter.toProductDTO(productEntity);
        Assert.assertEquals("productCodeTesting",productDTO1.getCode());
    }

    @Test
    @Order(2)
    public void TestSave(){

        Mockito.when(productConverter.toProductEntity(productDTO)).thenReturn(productEntity);
        Mockito.when(productConverter.toProductDTO(productEntity)).thenReturn(productDTO);
        Mockito.when(categoryRepository.findOneByCode(productDTO.getCategoryCode())).thenReturn(categoryEntity);
        Mockito.when(productRepository.save(productEntity)).thenReturn(productEntity);

        ProductDTO productDTO1 = productService.save(productDTO);

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
