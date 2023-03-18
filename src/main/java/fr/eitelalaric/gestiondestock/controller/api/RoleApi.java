package fr.eitelalaric.gestiondestock.controller.api;

import fr.eitelalaric.gestiondestock.dto.CategoryDto;
import fr.eitelalaric.gestiondestock.dto.RoleDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static fr.eitelalaric.gestiondestock.utils.Constants.APP_ROOT;


public interface RoleApi {
    @PostMapping(value = APP_ROOT+"/roles/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add/update a role", description = "This method allows to register or update a role", tags ="Role")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object role has created or updated successfully ",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = RoleDto.class))}),
            @ApiResponse(responseCode= "400", description = "The object role is not valid")
    })
    RoleDto save (@RequestBody RoleDto roleDto);

    @GetMapping(value = APP_ROOT+"/roles/{idrole}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search role", description = "This method allows to search a role by ID ", tags= "Role")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object role has found",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = RoleDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "The object role doesn't exist in the database")
    })
    RoleDto findById(@PathVariable("idrole") Integer idrole);

    @GetMapping(value = APP_ROOT+"/roles/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search all role", description = "This method allows to search the list of role in the database ", tags = "Role")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "List of role or empty list",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = RoleDto.class))
                    }),

    })
    List<RoleDto> findAll();

    @DeleteMapping(value = APP_ROOT+"/roles/delete/{idrole}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "delete a role", description = "This method allows to delete a role by ID ", tags = "Role")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The role was successfully deleted",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = RoleDto.class))
                    })
    })
    void delete(@PathVariable Integer id);
}
