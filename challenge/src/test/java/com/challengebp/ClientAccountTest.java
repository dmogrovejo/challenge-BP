package com.challengebp;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class ClientAccountTest {

	@Autowired
    private MockMvc mockMvc;

	
	@Test
    public void perform() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cuentas/")
                        .contentType(MediaType.APPLICATION_JSON));
    }
	
	/**
	 * Pruebas Integrales de todo el flujo  de informacion
	 * @throws Exception
	 */
	@Test
	public void whenCallAccountThenHttpStatus_OK() throws Exception{
		 mockMvc.perform(MockMvcRequestBuilders.get("/api/cuentas/")
                 .contentType(MediaType.APPLICATION_JSON))
         .andExpect(MockMvcResultMatchers
                 .status()
                 .isOk())
         .andExpect(MockMvcResultMatchers
                 .content()
                 .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}
}
