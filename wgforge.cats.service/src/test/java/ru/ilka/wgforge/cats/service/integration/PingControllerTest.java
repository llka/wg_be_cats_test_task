package ru.ilka.wgforge.cats.service.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.ilka.wgforge.cats.service.config.TestConfig;
import ru.ilka.wgforge.cats.service.controller.PingController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebMvcTest(PingController.class)
public class PingControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void tooManyRequestsTest() throws Exception {
            mvc.perform(get("/ping")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
    }
}
