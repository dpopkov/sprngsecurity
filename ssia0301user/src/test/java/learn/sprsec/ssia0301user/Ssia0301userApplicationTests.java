package learn.sprsec.ssia0301user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class Ssia0301userApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Test calling /hello endpoint without authentication returns unauthorized.")
    void helloUnauthenticated() throws Exception {
        mvc.perform(get("/hello"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Test calling /hello endpoint authenticated with a mock user returns ok.")
    @WithMockUser
    void helloAuthenticated() throws Exception {
        mvc.perform(get("/hello"))
                .andExpect(content().string("Hello!"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test calling /hello endpoint authenticating with wrong credentials returns unauthorized.")
    public void helloAuthenticatingWithWrongUser() throws Exception {
        mvc.perform(get("/hello")
                .with(httpBasic("bill","12345")))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Test calling /hello endpoint authenticating with valid credentials returns ok.")
    public void helloAuthenticatingWithValidUser() throws Exception {
        mvc.perform(get("/hello")
                .with(httpBasic("jane","123")))
                .andExpect(status().isOk());
    }
}
