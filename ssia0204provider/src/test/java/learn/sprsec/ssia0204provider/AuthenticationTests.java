package learn.sprsec.ssia0204provider;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Test calling /hello endpoint authenticating with valid credentials returns ok.")
    public void helloAuthenticatingWithValidUser() throws Exception {
        mvc.perform(get("/hello")
                .with(httpBasic("jane","1234")))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test calling /hello endpoint authenticating with wrong credentials returns unauthorized.")
    public void helloAuthenticatingWithInvalidUser() throws Exception {
        mvc.perform(get("/hello")
                .with(httpBasic("mary","12345")))
                .andExpect(status().isUnauthorized());
    }
}
