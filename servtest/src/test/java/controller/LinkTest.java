
package controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LinkTest {

    public static final String VALIDURL = "/hello/contacts";
    public static final String INVALIDURL_A = "hello/contacts";
    public static final String INVALIDURL_B = "/hellocontacts";
    public static final String INVALIDURL_C = "/hello/contact";
    public static final String INVALIDURL_D = "/contacts";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void methodShouldConnectToServer() throws Exception {
        this.mockMvc.perform(get(VALIDURL)).andDo(print()).andExpect(status().isOk());
    }
    @Test
    public void methodShouldShowNotFoundError() throws Exception {
        this.mockMvc.perform(get(INVALIDURL_A)).andDo(print()).andExpect(status().isNotFound());
        this.mockMvc.perform(get(INVALIDURL_B)).andDo(print()).andExpect(status().isNotFound());
        this.mockMvc.perform(get(INVALIDURL_C)).andDo(print()).andExpect(status().isNotFound());
        this.mockMvc.perform(get(INVALIDURL_D)).andDo(print()).andExpect(status().isNotFound());
    }
}


