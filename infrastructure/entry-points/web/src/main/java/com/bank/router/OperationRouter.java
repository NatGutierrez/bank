package com.bank.router;

import com.bank.data.OperationRequestDTO;
import com.bank.data.OperationResponseDTO;
import com.bank.handler.OperationHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
@Tag(name = "Operation", description = "Operation endpoints.")
public class OperationRouter {
    private final OperationHandler operationHandler;

    public OperationRouter(OperationHandler operationHandler) {
        this.operationHandler = operationHandler;
    }

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/operations",
                    method = RequestMethod.GET,
                    operation = @Operation(summary = "Get Operations", description = "List all operations.",
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "Successfully obtained all operations."),
                                    @ApiResponse(responseCode = "204", description = "No operations to get.")
                            }
                    )
            ),
            @RouterOperation(
                    path = "/operations/{id}",
                    method = RequestMethod.GET,
                    operation = @Operation(summary = "Get Operation by id", description = "Find a single operation by its id.",
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "Successfully obtained operation.",
                                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationResponseDTO.class))
                                    ),
                                    @ApiResponse(
                                            responseCode = "404", description = "Operation not found.",
                                            content = @Content(mediaType = "application/json")
                                    ),
                                    @ApiResponse(
                                            responseCode = "400", description = "Bad request. Validation error. Missing fields?",
                                            content = @Content(mediaType = "application/json")
                                    )
                            }
                    )
            ),
            @RouterOperation(
                    path = "/operations",
                    method = RequestMethod.POST,
                    operation = @Operation(summary = "Create new Operation", description = "Create a new operation.",
                            responses = {
                                    @ApiResponse(responseCode = "201", description = "Successfully created operation.",
                                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationResponseDTO.class))
                                    ),
                                    @ApiResponse(
                                            responseCode = "400", description = "Bad request. Validation error. Missing fields?",
                                            content = @Content(mediaType = "application/json")
                                    )
                            }
                    )
            ),
    })
    public RouterFunction<ServerResponse> operationRoutes() {
        return RouterFunctions
                .route(GET("/operations"), this::getAllOperations)
                .andRoute(GET("operations/{id}"), this::getOperationById)
                .andRoute(POST("/operations").and(accept(MediaType.APPLICATION_JSON)), this::createOperation);
    }

    public Mono<ServerResponse> getAllOperations(ServerRequest request) {
        return operationHandler.findAllOperations()
                .collectList()
                .flatMap(ops -> ServerResponse
                        .status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(ops));
    }

    public Mono<ServerResponse> getOperationById(ServerRequest request) {
        String opId = request.pathVariable("id");
        return operationHandler.findOperationById(opId)
                .flatMap(operationResponseDTO -> ServerResponse
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(operationResponseDTO));
    }

    public Mono<ServerResponse> createOperation(ServerRequest request) {
        return request.bodyToMono(OperationRequestDTO.class)
                .flatMap(operationHandler::createOperation)
                .flatMap(operationResponseDTO -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(operationResponseDTO));
    }
}
