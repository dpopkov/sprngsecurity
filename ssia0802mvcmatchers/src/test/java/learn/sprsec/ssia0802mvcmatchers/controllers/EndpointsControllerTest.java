package learn.sprsec.ssia0802mvcmatchers.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class EndpointsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Endpoints cannot be called unauthenticated")
    void testFailedAuthentication() throws Exception {
        mvc.perform(get("/a"))
                .andExpect(unauthenticated());
        mvc.perform(post("/a"))
                .andExpect(unauthenticated());
        mvc.perform(get("/a/b"))
                .andExpect(unauthenticated());
        mvc.perform(get("/a/b/c"))
                .andExpect(unauthenticated());
    }
}
