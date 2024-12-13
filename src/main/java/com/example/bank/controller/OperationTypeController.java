package com.example.bank.controller;

import com.example.bank.dto.OperationDTO;
import com.example.bank.dto.OperationTypeDTO;
import com.example.bank.service.OperationTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operation-types")
public class OperationTypeController {
    private final OperationTypeService operationTypeService;

    public OperationTypeController(OperationTypeService operationTypeService) {
        this.operationTypeService = operationTypeService;
    }

    @GetMapping
    public ResponseEntity<List<OperationTypeDTO>> getOperationTypes() {
        var response = operationTypeService.getAllOperationTypes();
        return response.isEmpty() ?
                ResponseEntity.status(204).build() : // no content
                ResponseEntity.status(200).body(response); // ok
    }

    @GetMapping("/{id}")
    public OperationTypeDTO getOperationTypeById(@PathVariable int id) {
        return operationTypeService.getOperationTypeById(id);
        /*return response.isEmpty() ?
                ResponseEntity.status(204).build() : // no content
                ResponseEntity.status(200).body(response); // ok*/
    }

    @PostMapping
    public ResponseEntity<OperationTypeDTO> createOperationType(@RequestBody OperationTypeDTO operationTypeDTO) {
        var response = operationTypeService.createOperationType(operationTypeDTO);
        return response != null ?
                ResponseEntity.status(201).body(response) : // created
                ResponseEntity.status(304).build(); // not modified
    }
}
