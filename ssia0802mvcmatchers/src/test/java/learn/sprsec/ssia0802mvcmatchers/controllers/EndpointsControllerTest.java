package learn.sprsec.ssia0802mvcmatchers.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Test
    @DisplayName("Any authenticated user can call GET /a")
    @WithUserDetails("jane")
    void testAnyAuthenticatedUserCanCallGetA() throws Exception {
        mvc.perform(get("/a"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Any user can call POST /a")
    void testAnyUserCanPostA() throws Exception {
        mvc.perform(post("/a"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Endpoints /a/b and /a/b/c are authorized for authenticated users")
    @WithUserDetails("jack")
    public void testOtherEndpointsStartingWithABAreAuthorized() throws Exception {
        mvc.perform(get("/a/b"))
                .andExpect(status().isOk());

        mvc.perform(get("/a/b/c"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Endpoints not starting with /a/b are denied")
    public void testOtherEndpointsAreUnauthorized() throws Exception {
        mvc.perform(get("/a/bc"))
                .andExpect(status().isUnauthorized());

        mvc.perform(get("/a/bd"))
                .andExpect(status().isUnauthorized());
    }
}
