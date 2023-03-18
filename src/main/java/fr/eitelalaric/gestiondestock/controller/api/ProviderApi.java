package fr.eitelalaric.gestiondestock.controller.api;

import fr.eitelalaric.gestiondestock.dto.CategoryDto;
import fr.eitelalaric.gestiondestock.dto.ProviderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static fr.eitelalaric.gestiondestock.utils.Constants.APP_ROOT;


public interface ProviderApi {
    @PostMapping(value = APP_ROOT+"/providers/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add/update a provider", description = "This method allows to register or update a provider", tags ="Provider")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object provider has created or updated successfully ",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProviderDto.class))}),
            @ApiResponse(responseCode= "400", description = "The object provider is not valid")
    })
    ProviderDto save (@RequestBody ProviderDto providerDto);

    @GetMapping(value = APP_ROOT+"/providers/{idprovider}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search provider", description = "This method allows to search a provider by ID ", tags= "Provider")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object provider has found",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProviderDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "The object provider doesn't exist in the database")
    })
    ProviderDto findById(@PathVariable("idprovider") Integer idprovider);


    @GetMapping(value = APP_ROOT+"/providers/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search all provider", description = "This method allows to search the list of provider in the database ", tags = "Provider")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "List of provider or empty list",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ProviderDto.class))
                    }),

    })
    List<ProviderDto> findAll();

    @DeleteMapping(value = APP_ROOT+"/providers/delete/{idprovider}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "delete a provider", description = "This method allows to delete a provider by ID ", tags = "Provider")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The provider was successfully deleted",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ProviderDto.class))
                    })
    })
    void delete(@PathVariable Integer id);
}
