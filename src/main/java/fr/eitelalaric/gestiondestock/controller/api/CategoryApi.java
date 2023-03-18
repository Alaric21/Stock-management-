package fr.eitelalaric.gestiondestock.controller.api;

import fr.eitelalaric.gestiondestock.dto.CategoryDto;
import fr.eitelalaric.gestiondestock.dto.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static fr.eitelalaric.gestiondestock.utils.Constants.APP_ROOT;


public interface CategoryApi {
    @PostMapping(value = APP_ROOT+"/categories/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add/update a category", description = "This method allows to register or update a category", tags ="Category")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object Category has created or updated successfully ",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))}),
            @ApiResponse(responseCode= "400", description = "The object Category is not valid")
    })
    CategoryDto save (@RequestBody CategoryDto categoryDto);

    @GetMapping(value = APP_ROOT+"/categories/{idcategory}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search category", description = "This method allows to search a category by ID ", tags= "Category")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object Category has found",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "The object Category doesn't exist in the database")
    })
    CategoryDto findById(@PathVariable("idcategory") Integer idcategory);

    @GetMapping(value = APP_ROOT+"/categories/code/{categorycode}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search category", description = "This method allows to search a category by CODE ", tags ="Category")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object Category has found",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))
                    }),
            @ApiResponse(responseCode = "404", description = "The object Category doesn't exist in the database")
    })
    CategoryDto findCategoryByCategoryCode(@PathVariable("categorycode") String categorycode);


    @GetMapping(value = APP_ROOT+"/categories/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search all category", description = "This method allows to search the list of category in the database ", tags = "Category")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "List of category or empty list",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))
                    }),

    })
    List<CategoryDto> findAll();

    @DeleteMapping(value = APP_ROOT+"/categories/delete/{idcategory}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "delete a category", description = "This method allows to delete a category by ID ", tags = "Category")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The category was successfully deleted",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))
                    })
    })
    void delete(@PathVariable Integer id);
}
