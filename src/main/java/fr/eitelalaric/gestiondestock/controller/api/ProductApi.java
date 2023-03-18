package fr.eitelalaric.gestiondestock.controller.api;

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
public interface ProductApi {
    @PostMapping(value = APP_ROOT+"/products/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add/update a product", description = "This method allows to register or update a  product", tags ="Product")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object Product has created or updated successfully ",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class))}),
            @ApiResponse(responseCode= "400", description = "The object Product is not valid")
    })
    ProductDto save (@RequestBody ProductDto productDto);

    @GetMapping(value = APP_ROOT+"/products/{idproduct}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search product", description = "This method allows to search a product by ID ", tags= "Product")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object Product has found",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "The object Product doesn't exist in the database")
    })
    ProductDto findById(@PathVariable("idproduct") Integer idproduct);

    @GetMapping(value = APP_ROOT+"/products/code/{productCode}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search product", description = "This method allows to search a product by CODE ", tags ="Product")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object Product has found",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class))
                    }),
            @ApiResponse(responseCode = "404", description = "The object Product doesn't exist in the database")
    })
    ProductDto findByProductCode(@PathVariable("productCode") String productcode);


    @GetMapping(value = APP_ROOT+"/products/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search all product", description = "This method allows to search the list of product in the database ", tags = "Product")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "List of product or empty list",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class))
                    }),

    })
    List<ProductDto> findAll();

    @DeleteMapping(value = APP_ROOT+"/products/delete/{idproduct}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "delete a product", description = "This method allows to delete a product by ID ", tags = "Product")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The product was successfully deleted",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class))
                    })
    })
    void delete(@PathVariable Integer id);
}
