package ar.gob.inti.argentinaprograma.miportfolio.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import ar.gob.inti.argentinaprograma.miportfolio.model.Profile;
import ar.gob.inti.argentinaprograma.miportfolio.service.IProfileService;

@AutoConfigureMockMvc
@SpringBootTest
public class ProfileControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProfileService _serviceProfile;

    @Test
    void shouldReturnListOfPersons() throws Exception {
        // List<Profile> profiles = new ArrayList<>(
        //         Arrays.asList(
        //                 new Profile("name1", "lastname1", "profile1", Date.valueOf("2022-04-05"), "about1", "title1"),
        //                 new Profile("name2", "lastname2",  "profile2", Date.valueOf("2022-04-05"), "about2", "title2"),
        //                 new Profile("name3", "lastname3",  "profile3", Date.valueOf("2022-04-05"), "about3", "title3")
        //         )
        // );

        // when(_serviceProfile.findAll()).thenReturn(profiles);

        // mockMvc.perform(get("/api/profile"))
        //         .andExpect(status().isOk())
        //         .andExpect(jsonPath("$.size()").value(profiles.size()))
        //         .andDo(print());
    }

@Test
void testGetAll() {
        
}
}