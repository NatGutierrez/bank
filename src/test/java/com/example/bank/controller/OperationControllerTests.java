package com.example.bank.controller;

import com.example.bank.dto.OperationRequestDTO;
import com.example.bank.dto.OperationResponseDTO;
import com.example.bank.service.OperationService;
import com.example.bank.utils.operations.OperationTypesEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OperationControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private OperationService operationService;

    private OperationRequestDTO operationRequest;
    private OperationResponseDTO operationResponse;

    @BeforeEach
    void setUp() {
        operationRequest = new OperationRequestDTO(BigDecimal.valueOf(100), OperationTypesEnum.ACCOUNT_DEPOSIT, "123");
        operationResponse = new OperationResponseDTO("123", BigDecimal.valueOf(100), OperationTypesEnum.ACCOUNT_DEPOSIT, "123");
    }

    @Test
    void createOperation_ReturnsCreated() throws Exception {
        when(operationService.createOperation(any(OperationRequestDTO.class))).thenReturn(operationResponse);

        mockMvc.perform(post("/operations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(operationRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("123"))
                .andExpect(jsonPath("$.value").value(100))
                .andExpect(jsonPath("$.type").value(OperationTypesEnum.ACCOUNT_DEPOSIT.name()));

        verify(operationService, times(1)).createOperation(any(OperationRequestDTO.class));
    }

    @Test
    void createOperation_ReturnsBadRequest() throws Exception {
        when(operationService.createOperation(any(OperationRequestDTO.class)))
                .thenThrow(new IllegalArgumentException("Request body not valid."));

        mockMvc.perform(post("/operations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(operationRequest)))
                .andExpect(status().isBadRequest());

        verify(operationService, times(1)).createOperation(any(OperationRequestDTO.class));
    }

    @Test
    void getAllOperations_ReturnsOperationsList() throws Exception {
        List<OperationResponseDTO> operationList = List.of(operationResponse);

        when(operationService.getAllOperations()).thenReturn(operationList);

        mockMvc.perform(get("/operations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("123"))
                .andExpect(jsonPath("$[0].value").value(100))
                .andExpect(jsonPath("$[0].type").value(OperationTypesEnum.ACCOUNT_DEPOSIT.name()));

        verify(operationService, times(1)).getAllOperations();
    }

    @Test
    void getAllOperations_ReturnsOperationsEmpty() throws Exception {
        when(operationService.getAllOperations()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/operations"))
                .andExpect(status().isNoContent());

        verify(operationService, times(1)).getAllOperations();
    }

    @Test
    void getById_ReturnsOperation() throws Exception {
        when(operationService.getOperationById("123")).thenReturn(operationResponse);

        mockMvc.perform(get("/operations/{id}", "123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("123"))
                .andExpect(jsonPath("$.value").value(100))
                .andExpect(jsonPath("$.type").value(OperationTypesEnum.ACCOUNT_DEPOSIT.name()));

        verify(operationService, times(1)).getOperationById("123");
    }

    @Test
    void getById_ReturnsNotFound() throws Exception {
        when(operationService.getOperationById("123"))
                .thenThrow(new NoSuchElementException());

        mockMvc.perform(get("/operations/{id}", "123"))
                .andExpect(status().isNotFound());

        verify(operationService, times(1)).getOperationById("123");
    }
}
