package ru.ilka.wgforge.cats.service.performance;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.ilka.wgforge.cats.service.config.TestConfig;
import ru.ilka.wgforge.cats.service.controller.PingController;
import ru.ilka.wgforge.cats.service.exception.RestException;
import ru.ilka.wgforge.cats.service.filter.RequestLimitFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@WebMvcTest(PingController.class)
@PropertySource("classpath:test_application.properties")
public class RequestsLimitTest {

    @Value("${limit.minute}")
    private long requestsLimitPerMinute;

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private TestConfig config;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(new RequestLimitFilter())
                .build();
    }

    @Test(expected = RestException.class)
    public void tooManyRequestsTest() throws Exception {
        for (int i = 0; i < requestsLimitPerMinute; i++) {
            mvc.perform(get("/ping")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
        mvc.perform(get("/ping")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.TOO_MANY_REQUESTS.value()));
    }
}
