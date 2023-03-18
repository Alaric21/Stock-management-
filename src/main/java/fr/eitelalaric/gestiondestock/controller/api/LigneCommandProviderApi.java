package fr.eitelalaric.gestiondestock.controller.api;

import fr.eitelalaric.gestiondestock.dto.LigneCommandeProviderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static fr.eitelalaric.gestiondestock.utils.Constants.APP_ROOT;


public interface LigneCommandProviderApi {
    @PostMapping(value = APP_ROOT+"/lignecommandproviders/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add/update a Ligne command provider", description = "This method allows to register or update a Ligne command provider", tags ="Ligne-command-provider")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object Ligne command provider has created or updated successfully ",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = LigneCommandeProviderDto.class))}),
            @ApiResponse(responseCode= "400", description = "The object Ligne command provider is not valid")
    })
    LigneCommandeProviderDto save (@RequestBody LigneCommandeProviderDto ligneCommandeProviderDto);

    @GetMapping(value = APP_ROOT+"/lignecommandproviders/{idlignecommandprovider}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search Ligne command provider", description = "This method allows to search a Ligne command provider by ID ", tags= "Ligne-command-provider")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object Ligne command provider has found",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LigneCommandeProviderDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "The object Ligne command provider doesn't exist in the database")
    })
    LigneCommandeProviderDto findById(@PathVariable("idlignecommandprovider") Integer idlignecommandprovider);


    @GetMapping(value = APP_ROOT+"/lignecommandproviders/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search all Ligne command provider", description = "This method allows to search the list of Ligne command provider in the database ", tags = "Ligne-command-provider")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "List of Ligne command provider or empty list",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = LigneCommandeProviderDto.class))
                    }),

    })
    List<LigneCommandeProviderDto> findAll();

    @DeleteMapping(value = APP_ROOT+"/lignecommandproviders/{idlignecommandprovider}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "delete a Ligne command provider", description = "This method allows to delete a Ligne command provider by ID ", tags = "Ligne-command-provider")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The Ligne command provider was successfully deleted",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = LigneCommandeProviderDto.class))
                    })
    })
    void delete(@PathVariable Integer idlignecommandprovider);
}
