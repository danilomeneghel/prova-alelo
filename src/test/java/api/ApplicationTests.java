package api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith( SpringRunner.class )
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource( locations = "classpath:application-test.properties" )
public class ApplicationTests {

    @Test
    public void contextLoads() {
    }

}