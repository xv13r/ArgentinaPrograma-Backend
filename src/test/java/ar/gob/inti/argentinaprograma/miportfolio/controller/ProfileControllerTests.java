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
}
