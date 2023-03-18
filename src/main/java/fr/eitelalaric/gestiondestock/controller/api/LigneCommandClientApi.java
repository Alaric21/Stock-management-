package fr.eitelalaric.gestiondestock.controller.api;

import fr.eitelalaric.gestiondestock.dto.CategoryDto;
import fr.eitelalaric.gestiondestock.dto.LigneCommandeClientDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static fr.eitelalaric.gestiondestock.utils.Constants.APP_ROOT;


public interface LigneCommandClientApi {
    @PostMapping(value = APP_ROOT+"/lignecommandclients/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add/update a Ligne command Client", description = "This method allows to register or update a Ligne command Client", tags ="Ligne-command-Client")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object Ligne command Client has created or updated successfully ",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = LigneCommandeClientDto.class))}),
            @ApiResponse(responseCode= "400", description = "The object Ligne command Client is not valid")
    })
    LigneCommandeClientDto save (@RequestBody LigneCommandeClientDto ligneCommandeClientDto);

    @GetMapping(value = APP_ROOT+"/lignecommandclients/{idlignecommandclient}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search Ligne command Client", description = "This method allows to search a Ligne command Client by ID ", tags= "Ligne-command-Client")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object Ligne command Client has found",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = LigneCommandeClientDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "The object Ligne command Client doesn't exist in the database")
    })
    LigneCommandeClientDto findById(@PathVariable("idlignecommandclient") Integer idlignecommandclient);


    @GetMapping(value = APP_ROOT+"/lignecommandclients/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search all Ligne command Client", description = "This method allows to search the list of Ligne command Client in the database ", tags = "Ligne-command-Client")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "List of Ligne command Client or empty list",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = LigneCommandeClientDto.class))
                    }),

    })
    List<LigneCommandeClientDto> findAll();

    @DeleteMapping(value = APP_ROOT+"/lignecommandclients/{idlignecommandclient}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "delete a Ligne command Client", description = "This method allows to delete a Ligne command Client by ID ", tags = "Ligne-command-Client")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The Ligne command Client was successfully deleted",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = LigneCommandeClientDto.class))
                    })
    })
    void delete(@PathVariable Integer idlignecommandclient);
}
