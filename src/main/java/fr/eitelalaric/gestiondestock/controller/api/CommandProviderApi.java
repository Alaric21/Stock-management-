package fr.eitelalaric.gestiondestock.controller.api;

import fr.eitelalaric.gestiondestock.dto.CategoryDto;
import fr.eitelalaric.gestiondestock.dto.CommandeProviderDto;
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


public interface CommandProviderApi {
    @PostMapping(value = APP_ROOT+"/commandProviders/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add/update a command provider", description = "This method allows to register or update a command provider", tags ="Command-Provider")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object command provider has created or updated successfully ",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CommandeProviderDto.class))}),
            @ApiResponse(responseCode= "400", description = "The object command provider is not valid")
    })
    CommandeProviderDto save (@RequestBody CommandeProviderDto commandeProviderDto);

    @GetMapping(value = APP_ROOT+"/commandProviders/{idcommand}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search command provider", description = "This method allows to search a command provider by ID ", tags= "Command-Provider")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object command provider has found",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CommandeProviderDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "The object command provider doesn't exist in the database")
    })
    CommandeProviderDto findById(@PathVariable("idcommand") Integer idcommand);

    @GetMapping(value = APP_ROOT+"/commandProvider/code/{commandcode}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search command provider", description = "This method allows to search a command provider by CODE ", tags ="Command-Provider")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object command provider has found",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CommandeProviderDto.class))
                    }),
            @ApiResponse(responseCode = "404", description = "The object command provider doesn't exist in the database")
    })
    CommandeProviderDto findCommandeProviderByCode(@PathVariable("commandcode") String commandcode);



    @GetMapping(value = APP_ROOT+"/commandProviders/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search all command provider", description = "This method allows to search the list of command provider in the database ", tags = "Command-Provider")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "List of command provider or empty list",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CommandeProviderDto.class))
                    }),

    })
    List<CommandeProviderDto> findAll();

    @DeleteMapping(value = APP_ROOT+"/commandProvider/delete/{idprovider}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "delete a command provider", description = "This method allows to delete a command provider by ID ", tags = "Command-Provider")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The command provider was successfully deleted",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CommandeProviderDto.class))
                    })
    })
    void delete(@PathVariable Integer id);
}
