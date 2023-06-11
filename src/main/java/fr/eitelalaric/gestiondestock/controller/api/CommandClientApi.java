package fr.eitelalaric.gestiondestock.controller.api;

import fr.eitelalaric.gestiondestock.dto.CommandeClientDto;
import fr.eitelalaric.gestiondestock.dto.LigneCommandeClientDto;
import fr.eitelalaric.gestiondestock.model.EtatCommande;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static fr.eitelalaric.gestiondestock.utils.Constants.APP_ROOT;


public interface CommandClientApi {
    @PostMapping(value = APP_ROOT+"/commandClients/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add/update a command client", description = "This method allows to register or update a command client", tags ="Command-Client")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object command client has created or updated successfully ",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CommandeClientDto.class))}),
            @ApiResponse(responseCode= "400", description = "The object command client is not valid")
    })
    CommandeClientDto save (@RequestBody CommandeClientDto commandeClientDto);

    @PatchMapping(value = APP_ROOT+"/commandClients/update/etat/{idcommande}/{etatcommande}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add/update a command client", description = "This method allows to register or update a command client", tags ="Command-Client")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object command client has created or updated successfully ",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CommandeClientDto.class))}),
            @ApiResponse(responseCode= "400", description = "The object command client is not valid")
    })
    CommandeClientDto updateEtatCommande (@PathVariable("idcommande") Integer idcommande, @PathVariable("etatcommande") EtatCommande etatcommande);

    @PatchMapping(value = APP_ROOT+"/commandClients/update/quantite/{idcommande}/{idLigneCommande}/{quantite}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add/update a command client", description = "This method allows to register or update a command client", tags ="Command-Client")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object command client has created or updated successfully ",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CommandeClientDto.class))}),
            @ApiResponse(responseCode= "400", description = "The object command client is not valid")
    })
    CommandeClientDto updateQuanditeCommande(@PathVariable Integer idcommande,@PathVariable Integer idLigneCommande,@PathVariable BigDecimal quantite);

    @PatchMapping(value = APP_ROOT+"/commandClients/update/client/{idcommande}/{idClient}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add/update a command client", description = "This method allows to register or update a command client", tags ="Command-Client")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object command client has created or updated successfully ",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CommandeClientDto.class))}),
            @ApiResponse(responseCode= "400", description = "The object command client is not valid")
    })
    CommandeClientDto updateClient(@PathVariable Integer idcommande, @PathVariable Integer idClient);

    @PatchMapping(value = APP_ROOT+"/commandClients/update/product/{idcommande}/{idlignecommande}/{idproduct}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add/update a command client", description = "This method allows to register or update a command client", tags ="Command-Client")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object command client has created or updated successfully ",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CommandeClientDto.class))}),
            @ApiResponse(responseCode= "400", description = "The object command client is not valid")
    })
    CommandeClientDto updateProduct(@PathVariable Integer idcommande, @PathVariable Integer idlignecommande,@PathVariable Integer idproduct );

    @DeleteMapping(value = APP_ROOT+"/commandClients/delete/product/{idcommande}/{idlignecommande}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add/update a command client", description = "This method allows to register or update a command client", tags ="Command-Client")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object command client has created or updated successfully ",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CommandeClientDto.class))}),
            @ApiResponse(responseCode= "400", description = "The object command client is not valid")
    })
    CommandeClientDto deleteProdutFromCommand(@PathVariable Integer idcommande, @PathVariable Integer idlignecommande );

    @GetMapping(value = APP_ROOT+"/commandClients/{idcommand}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search command client", description = "This method allows to search a command client by ID ", tags= "Command-Client")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object command client has found",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CommandeClientDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "The object command client doesn't exist in the database")
    })
    CommandeClientDto findById(@PathVariable("idcommand") Integer idcommand);

    @GetMapping(value = APP_ROOT+"/commandClient/code/{commandcode}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search command client", description = "This method allows to search a command client by CODE ", tags ="Command-Client")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object command client has found",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CommandeClientDto.class))
                    }),
            @ApiResponse(responseCode = "404", description = "The object command client doesn't exist in the database")
    })
    CommandeClientDto findCommandeClientByCode(@PathVariable("commandcode") String commandcode);



    @GetMapping(value = APP_ROOT+"/commandClients/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search all command client", description = "This method allows to search the list of command client in the database ", tags = "Command-Client")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "List of command client or empty list",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CommandeClientDto.class))
                    }),

    })
    List<CommandeClientDto> findAll();

    @GetMapping(value = APP_ROOT+"/commandClients/lignecommandeclient/{idcommande}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search all command ligne client", description = "This method allows to search the list of command ligne client in the database ", tags = "Command-Client")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "List of command line client or empty list",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = LigneCommandeClientDto.class))
                    }),

    })
    List<LigneCommandeClientDto> findAllLigneCommandeClientByCommandeClientId(@PathVariable("idCommande") Integer idCommande);




    @DeleteMapping(value = APP_ROOT+"/commandClient/delete/{idclient}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "delete a command client", description = "This method allows to delete a command client by ID ", tags = "Command-Client")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The command client was successfully deleted",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CommandeClientDto.class))
                    })
    })
    void delete(@PathVariable Integer id);
}
