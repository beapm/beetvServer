package net.ausiasmarch.beetvServer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BeetvServerApplicationTests {

    @Test
    public void testAppController() {
        AppController homeController = new AppController();
        String result = homeController.info().getBody();
        assertEquals(result, "Welcome to BeeTV Server");
    }

}
