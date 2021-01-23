package net.ausiasmarch.beetvServer;

import net.ausiasmarch.beetvServer.api.AppController;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest (classes = BeetvServerApplicationTests.class)
class BeetvServerApplicationTests {

    @Test
    public void testAppController() {
        AppController homeController = new AppController();
        String result = homeController.info().getBody();
        assertEquals(result, "Welcome to BeeTV Server");
    }

}
