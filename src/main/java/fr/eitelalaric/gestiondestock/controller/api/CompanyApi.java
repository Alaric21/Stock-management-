package fr.eitelalaric.gestiondestock.controller.api;

import fr.eitelalaric.gestiondestock.dto.CompanyDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static fr.eitelalaric.gestiondestock.utils.Constants.APP_ROOT;


public interface CompanyApi {
    @PostMapping(value = APP_ROOT+"/company/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add/update a company", description = "This method allows to register or update a company", tags ="Company")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object company has created or updated successfully ",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyDto.class))}),
            @ApiResponse(responseCode= "400", description = "The object company is not valid")
    })
    CompanyDto save (@RequestBody CompanyDto companyDto);

    @GetMapping(value = APP_ROOT+"/company/{idcompany}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search company", description = "This method allows to search a company by ID ", tags= "Company")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object company has found",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "The object company doesn't exist in the database")
    })
    CompanyDto findById(@PathVariable("idcompany") Integer idcompany);


    @GetMapping(value = APP_ROOT+"/company/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search all company", description = "This method allows to search the list of company in the database ", tags = "Company")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "List of company or empty list",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyDto.class))
                    }),

    })
    List<CompanyDto> findAll();

    @DeleteMapping(value = APP_ROOT+"/categories/delete/{idcompany}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "delete a company", description = "This method allows to delete a company by ID ", tags = "Company")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The company was successfully deleted",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyDto.class))
                    })
    })
    void delete(@PathVariable Integer id);
}
