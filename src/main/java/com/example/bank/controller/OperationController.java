package com.example.bank.controller;

import com.example.bank.dto.OperationRequestDTO;
import com.example.bank.dto.OperationResponseDTO;
import com.example.bank.service.OperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Operation endpoints", description = "")
@RestController
@RequestMapping("/operations")
public class OperationController {
    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @Operation(summary = "Get Operations", description = "List all operations.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully obtained all operations."),
            @ApiResponse(responseCode = "204", description = "No operations to get.")
    })
    @GetMapping
    public ResponseEntity<List<OperationResponseDTO>> getOperations() {
        var response = operationService.getAllOperations();
        return response.isEmpty() ?
                ResponseEntity.status(204).build() : // no content
                ResponseEntity.status(200).body(response); // ok
    }

    @Operation(summary = "Get Operation by id", description = "Find a single operation by its id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully obtained operation."),
            @ApiResponse(responseCode = "404", description = "Operation not found.")
    })
    @GetMapping("/{id}")
    public OperationResponseDTO getOperationById(@PathVariable String id) {
        return operationService.getOperationById(id);
        /*return response.isEmpty() ?
                ResponseEntity.status(204).build() : // no content
                ResponseEntity.status(200).body(response); // ok*/
    }

    @Operation(summary = "Create new Operation", description = "Create a new operation.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Successfully created operation.")
    })
    @PostMapping
    public ResponseEntity<OperationResponseDTO> createOperation(@Valid @RequestBody OperationRequestDTO operationDTO) {
        var response = operationService.createOperation(operationDTO);
        return response != null ?
                ResponseEntity.status(201).body(response) : // created
                ResponseEntity.status(304).build(); // not modified
    }
}