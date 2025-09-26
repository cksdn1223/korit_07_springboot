package com.example.cardatabase;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.cardatabase.domain.AppUserRepository;
import com.example.cardatabase.domain.CarRepository;
import com.example.cardatabase.domain.OwnerRepository;
import com.example.cardatabase.web.CarController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;

// 테스트할 컨트롤러를 지정합니다.
// 이 어노테이션은 MVC 관련 구성 요소만 로드합니다.
@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OwnerRepository ownerRepository;
    @MockitoBean
    private CarRepository carRepository;

    @Test
    void getCars_returnsOkStatus() throws Exception {
        mockMvc.perform(get("/cars"))
                .andExpect(status().isOk());
    }
}