package fr.eitelalaric.gestiondestock.controller.api;

import fr.eitelalaric.gestiondestock.dto.CategoryDto;
import fr.eitelalaric.gestiondestock.dto.VenteDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static fr.eitelalaric.gestiondestock.utils.Constants.APP_ROOT;


public interface VenteApi {
    @PostMapping(value = APP_ROOT+"/ventes/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add/update a sale", description = "This method allows to register or update a sale", tags ="Sale")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object sale has created or updated successfully ",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = VenteDto.class))}),
            @ApiResponse(responseCode= "400", description = "The object sale is not valid")
    })
    VenteDto save (@RequestBody VenteDto venteDto);

    @GetMapping(value = APP_ROOT+"/ventes/{idvente}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search sale", description = "This method allows to search a sale by ID ", tags= "Sale")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object sale has found",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = VenteDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "The object sale doesn't exist in the database")
    })
    VenteDto findById(@PathVariable("idvente") Integer idvente);

    @GetMapping(value = APP_ROOT+"/ventes/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search all sale", description = "This method allows to search the list of sale in the database ", tags = "Sale")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "List of sale or empty list",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = VenteDto.class))
                    }),

    })
    List<VenteDto> findAll();

    @DeleteMapping(value = APP_ROOT+"/categories/delete/{idvente}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "delete a sale", description = "This method allows to delete a sale by ID ", tags = "Sale")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The sale was successfully deleted",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = VenteDto.class))
                    })
    })
    void delete(@PathVariable Integer id);
}
