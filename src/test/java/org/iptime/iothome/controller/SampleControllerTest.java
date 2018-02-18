package org.iptime.iothome.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@WebMvcTest(SampleController.class)         //@Controller, @ControllerAdvice, @Component를 scan
public class SampleControllerTest {
    
    @Autowired
    MockMvc mock;
    
    @Test
    public void helloTest() throws Exception {
        /***given***/
        mock.perform(get("/hello")).andExpect(content().string("Hello World"));
        
        /***when***/

        /***then***/
    }
    
    @Test
    public void helloDetailTest() throws Exception {
        /***given***/
        MvcResult result = mock.perform(get("/hello"))
            .andExpect(status().isOk())
            .andExpect(content().string("Hello World")).andReturn();
        /***when***/

        /***then***/
        System.out.println(result.getResponse().getContentAsString());
    }
}
