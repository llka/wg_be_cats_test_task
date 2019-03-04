package ru.ilka.wgforge.cats.service.handler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ilka.wgforge.cats.service.exception.RestException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExceptionHandlerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test(expected = RestException.class)
    public void shouldThrowRestException() {
        testRestTemplate.getForObject("http://localhost:8088/api/v1/cats", String.class);
    }
}
