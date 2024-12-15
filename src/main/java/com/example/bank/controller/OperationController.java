package com.example.bank.controller;

import com.example.bank.dto.OperationDTO;
import com.example.bank.service.OperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operations")
public class OperationController {
    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping
    public ResponseEntity<List<OperationDTO>> getOperations() {
        var response = operationService.getAllOperations();
        return response.isEmpty() ?
                ResponseEntity.status(204).build() : // no content
                ResponseEntity.status(200).body(response); // ok
    }

    @GetMapping("/{id}")
    public OperationDTO getOperationById(@PathVariable int id) {
        return operationService.getOperationById(id);
        /*return response.isEmpty() ?
                ResponseEntity.status(204).build() : // no content
                ResponseEntity.status(200).body(response); // ok*/
    }

    @PostMapping
    public ResponseEntity<OperationDTO> createOperation(@RequestBody OperationDTO operationDTO) {
        var response = operationService.createOperation(operationDTO);
        return response != null ?
                ResponseEntity.status(201).body(response) : // created
                ResponseEntity.status(304).build(); // not modified
    }
}
