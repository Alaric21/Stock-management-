package fr.eitelalaric.gestiondestock.controller.api;

import fr.eitelalaric.gestiondestock.dto.CategoryDto;
import fr.eitelalaric.gestiondestock.dto.ClientDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static fr.eitelalaric.gestiondestock.utils.Constants.APP_ROOT;


public interface ClientApi {
    @PostMapping(value = APP_ROOT+"/client/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add/update a client", description = "This method allows to register or update a client", tags ="Client")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object client has created or updated successfully ",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDto.class))}),
            @ApiResponse(responseCode= "400", description = "The object client is not valid")
    })
    ClientDto save (@RequestBody ClientDto categoryDto);

    @GetMapping(value = APP_ROOT+"/categories/{idclient}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search client", description = "This method allows to search a client by ID ", tags= "Client")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object client has found",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "The object client doesn't exist in the database")
    })
    ClientDto findById(@PathVariable("idclient") Integer idclient);


    @GetMapping(value = APP_ROOT+"/clients/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search all client", description = "This method allows to search the list of client in the database ", tags = "Client")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "List of client or empty list",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDto.class))
                    }),

    })
    List<ClientDto> findAll();

    @DeleteMapping(value = APP_ROOT+"/categories/delete/{idclient}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "delete a client", description = "This method allows to delete a client by ID ", tags = "Client")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The client was successfully deleted",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDto.class))
                    })
    })
    void delete(@PathVariable Integer id);
}
