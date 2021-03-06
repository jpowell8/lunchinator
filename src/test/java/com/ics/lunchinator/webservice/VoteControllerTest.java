package com.ics.lunchinator.webservice;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author joshpowell
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class VoteControllerTest {

  @Autowired
  private WebApplicationContext context;

  private MockMvc mockMvc;

  @Before
  public void setup() throws Exception {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

    @Test
  public void givenGreetURIWithPost_whenMockMVC_thenVerifyResponse() throws Exception {
//    this.mockMvc.perform(post("/api/vote")).andDo(print())
//        //mock service call
//        //with full vote body
//        //with correct headers
//        .andExpect(status().isCreated());
//    //and no body
  }

  //with empty body

  //with optional null fields.

  //with different header...


  //Example Test from Docs.
//  @Test
//  public void givenGreetURIWithPost_whenMockMVC_thenVerifyResponse() throws Exception {
//    this.mockMvc.perform(post("/greetWithPost")).andDo(print())
//        .andExpect(status().isOk()).andExpect(content()
//        .contentType("application/json;charset=UTF-8"))
//        .andExpect(jsonPath("$.message").value("Hello World!!!"));
//  }

}

