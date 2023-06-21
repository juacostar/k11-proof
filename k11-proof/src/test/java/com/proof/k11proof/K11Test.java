package com.proof.k11proof;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proof.k11proof.dto.AddUserDTO;
import com.proof.k11proof.model.User;
import com.proof.k11proof.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = K11ProofApplication.class)
@AutoConfigureMockMvc
public class K11Test {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    private final Jackson2ObjectMapperBuilder mapperBuilder = new Jackson2ObjectMapperBuilder();
    private final ObjectMapper objectMapper = mapperBuilder.build();

    @Test
    void getUser() throws Exception{
        String uri = "/k11/api/users/8";
        User userExpected = userService.findOneUser(8);

        HttpHeaders headers = new HttpHeaders();
        headers.set("client_id","clientIdPrueba");
        headers.set("client_secret","clientSecretPrueba");

        ResultActions resultActions = this.mockMvc.perform(get(uri).headers(headers)).andExpect(status().isOk());
        MvcResult mvcResult = resultActions.andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        User responseObtained = objectMapper.readValue(response, User.class);
        assertEquals(responseObtained.getId(), userExpected.getId());

    }

    @Test
    void createUser() throws Exception{
        AddUserDTO addUserDTO = new AddUserDTO();
        addUserDTO.setFirst_name("juan");
        addUserDTO.setEmail("correo_prueba@gmail.com");

        String uri = "/k11/api/users";
        User userExpected = userService.addUser(addUserDTO);


        String requestJson = objectMapper.writeValueAsString(addUserDTO);
        ResultActions resultActions = this.mockMvc.perform(post(uri).contentType(MediaType.APPLICATION_JSON).content(requestJson)).andExpect(status().isOk());
        MvcResult mvcResult = resultActions.andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        User responseObtained = objectMapper.readValue(response, User.class);
        assertEquals(responseObtained.getEmail(), userExpected.getEmail());
    }
}
