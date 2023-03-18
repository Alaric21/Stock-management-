package fr.eitelalaric.gestiondestock.controller.api;

import fr.eitelalaric.gestiondestock.dto.EmployeeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static fr.eitelalaric.gestiondestock.utils.Constants.APP_ROOT;


public interface UserApi {
    @PostMapping(value = APP_ROOT+"/users/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add/update a employee", description = "This method allows to register or update a employee", tags ="Employee")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object employee has created or updated successfully ",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDto.class))}),
            @ApiResponse(responseCode= "400", description = "The object employee is not valid")
    })
    EmployeeDto save (@RequestBody EmployeeDto employeeDto);

    @GetMapping(value = APP_ROOT+"/users/{iduser}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search employee", description = "This method allows to search a employee by ID ", tags= "Employee")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The object employee has found",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDto.class))
            }),
            @ApiResponse(responseCode = "404", description = "The object employee doesn't exist in the database")
    })
    EmployeeDto findById(@PathVariable("iduser") Integer iduser);


    @GetMapping(value = APP_ROOT+"/users/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "search all employee", description = "This method allows to search the list of employee in the database ", tags = "Employee")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "List of employee or empty list",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDto.class))
                    }),

    })
    List<EmployeeDto> findAll();

    @DeleteMapping(value = APP_ROOT+"/categories/delete/{iduser}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "delete a employee", description = "This method allows to delete a employee by ID ", tags = "Employee")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "The employee was successfully deleted",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeDto.class))
                    })
    })
    void delete(@PathVariable Integer id);
}
