package com.learning.spring.rest.employees.controller;


import com.learning.spring.rest.employees.dto.BaseCommunityDTO;
import com.learning.spring.rest.employees.mappers.CommunityMapper;
import com.learning.spring.rest.employees.model.Community;
import com.learning.spring.rest.employees.services.CommunityServiceImpl;
import com.learning.spring.rest.employees.utils.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.learning.spring.rest.employees.utils.Constants.COMMUNITY_ADDED;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class CommunityControllerTest {

    @Mock
    private CommunityServiceImpl communityService;

    @Mock
    private Response response;

    @InjectMocks
    CommunityController communityController;

    private MockMvc mockMvc;


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(communityController).build();
    }

    @Test
    public void addCommunityTest() throws Exception {

        BaseCommunityDTO baseCommunityDTO = new BaseCommunityDTO("ABC");
        when(communityService.addCommunity(baseCommunityDTO)).thenReturn(baseCommunityDTO);
        doNothing().when(response).setMessage(COMMUNITY_ADDED);


        mockMvc.perform(post("/community")
                .contentType("application/json")
                .content("{\n" +
                        "    \"communityName\": \"IT\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

}
