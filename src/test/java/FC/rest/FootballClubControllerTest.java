package FC.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc

public class FootballClubControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void ReturnListOfFootballClubs() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/footballclubs")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("FC Barcelona"))
                .andExpect(jsonPath("$[0].stadiumCapacity").value(6))
                .andExpect(jsonPath("$[0].location").value("Location"))
                .andExpect(jsonPath("$[0].ground").value("Camp Nou"));

    }

    @Test
    public void ReturnCorrectFootballClubWhenExist() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/footballclubs/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("FC Barcelona"))
                .andExpect(jsonPath("$.stadiumCapacity").value(6))
                .andExpect(jsonPath("$.location").value("Location"))
                .andExpect(jsonPath("$.ground").value("Camp Nou"));
    }

    @Test
    public void ErrorWhenFootballClubDoesntExist() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/footballclubs/4")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}
