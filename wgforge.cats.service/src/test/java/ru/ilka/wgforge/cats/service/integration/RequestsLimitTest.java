package ru.ilka.wgforge.cats.service.integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.ilka.wgforge.cats.service.controller.PingController;
import ru.ilka.wgforge.cats.service.exception.RestException;
import ru.ilka.wgforge.cats.service.filter.RequestLimitFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PingController.class)
public class RequestsLimitTest {
    @Value("${limit.minute}")
    private long limitPerMinute;

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(new RequestLimitFilter())
                .build();
    }

    @Test(expected = RestException.class)
    public void shouldThrowRestException() throws Exception {
        for (int i = 0; i < limitPerMinute; i++) {
            mvc.perform(get("/ping")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
        mvc.perform(get("/ping")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.TOO_MANY_REQUESTS.value()));
    }
}
