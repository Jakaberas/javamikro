package hu.yokudlela.table.rest;


import hu.yokudlela.media.java.clients.api.ImageControllerApi;
import hu.yokudlela.media.java.clients.model.ImageProcessModel;
import hu.yokudlela.media.java.clients.invoker.ApiException;
import hu.yokudlela.table.datamodel.Table;
import hu.yokudlela.table.service.BusinessException;
import hu.yokudlela.table.service.cliens.MediaImageControllerService;
import hu.yokudlela.table.utils.validation.ApiError;
import hu.yokudlela.table.store.TableRepository;
import hu.yokudlela.table.utils.logging.AspectLogger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Access;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author (K)riszti??n
 */
@RestController()
@RequestMapping(path = "/admin")
@Validated
@Slf4j
public class AdminController {

    @Autowired(required = true)
    TableRepository tableService;
    
    @Autowired
    MediaImageControllerService  mediaClient;
    
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "A keresett asztal", 
	    content = { @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = Table.class)) }),
	@ApiResponse(responseCode = "400", description = "Nem megfelel?? k??r??s param??terek (valid??ci??s hiva)", 
	    content = { @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = ApiError.class)) }),
	@ApiResponse(responseCode = "500", description = "Sikertelen lek??rdez??s", 
	    content = { @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = ApiError.class)) })            
    })
    @Cacheable(cacheNames = "tablebyid", key = "#pId")
    @Operation(summary = "Asztal lek??rdez??s n??v alapj??n")
    @GetMapping(path = "/getbyname/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Table getByName(
        @Parameter(description="Asztal neve", required = false, example = "A1")
        @PathVariable(name = "name", required = false)
        @NotEmpty(message = "error.table.name.notset")
        @NotBlank(message = "error.table.name.notset")
        @Size(min=2, max = 10, message = "error.table.name.short_or_long")
        @Pattern(regexp = "^[A-Z][a-zA-Z0-9]+$", message = "error.table.name.pattern_is_bad")
        String pId) throws Exception {
        return tableService.findByName(pId);
    }
    

    
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Sikeres lek??rdez??s", 
	    content = { @Content(mediaType = "application/json",  
	    array = @ArraySchema(schema = @Schema(implementation = Table.class))) }),
	@ApiResponse(responseCode = "500", description = "Sikertelen lek??rdez??s", 
	    content = { @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = ApiError.class)) })            
    })    
    @Operation(summary = "Asztalok lek??rdez??s st??tusz alapj??n")
    @Cacheable(cacheNames = "tables", key = "#root.methodName")
    @GetMapping(path = "/getAvailableTables", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Table> getAvailableTables() throws Exception {
        return tableService.findByAvailable(true);
    }
    
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Sikeres lek??rdez??s", 
	    content = { @Content(mediaType = "application/json",  
	    array = @ArraySchema(schema = @Schema(implementation = Table.class))) }),
	@ApiResponse(responseCode = "500", description = "Sikertelen lek??rdez??s", 
	    content = { @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = ApiError.class)) })            
    })    
    @Operation(summary = "Asztalok lek??rdez??s st??tusz alapj??n")
    @Cacheable(cacheNames = "tables", key = "#root.methodName")
    @GetMapping(path = "/getNotAvailableTables", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Table> getNotAvailableTables() throws Exception {
        return tableService.findByAvailable(false);
    }

    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Sikeres felvitel", 
	    content = { @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = Table.class)) }),
	@ApiResponse(responseCode = "403", description = "Nincs megfelel?? jogosults??god",
	    content = { @Content(mediaType = "application/json") }),
      	@ApiResponse(responseCode = "401", description = "Lej??rt token",
	    content = { @Content(mediaType = "application/json") }),
      	@ApiResponse(responseCode = "302", description = "Nincs bejelentkezve, ??tir??ny??t??s a login oldalra",
	    content = { @Content(mediaType = "application/json") }),
      	@ApiResponse(responseCode = "400", description = "Nem megfelel?? k??r??s param??terek (valid??ci??s hiva)", 
	    content = { @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = ApiError.class)) }),
    	@ApiResponse(responseCode = "500", description = "Asztal m??r l??tezik",
	    content = { @Content(mediaType = "application/json") })
    })
    @Operation(summary = "??j aztal felvitele",
            security = {
            @SecurityRequirement(name = "apikey",scopes = {"table"}),
            @SecurityRequirement(name = "openid",scopes = {"table"}),
            @SecurityRequirement(name = "oauth2",scopes = {"table"}),
    })    
    @Caching(
            evict = {
                @CacheEvict(cacheNames = "tables", key = "'getNotAvailableTables'"),
                @CacheEvict(cacheNames = "tables", key = "'getAvailableTables'")
        },
            put = {
                @CachePut(cacheNames = "tablebyid", key = "#pData.getName()")
                    
            })    
    
    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public Table add(
            Principal principal,
           @Valid
            @Parameter(description = "Az ??j asztal",required = true) @RequestBody(required = true) Table pData) throws Exception {
//        KeycloakPrincipal kPrincipal = (KeycloakPrincipal) principal;
//        AccessToken token = kPrincipal.getKeycloakSecurityContext().getToken();
//        Access customClaims = (Access) token.getResourceAccess("account");
//        System.out.println("ROLES:"+customClaims.value());
        tableService.save(pData);
        return pData;
    }

        
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Sikeres m??velet", 
	    content = { @Content(mediaType = "application/json") }),
	@ApiResponse(responseCode = "403", description = "Nincs megfelel?? jogosults??god",
	    content = { @Content(mediaType = "application/json") }),
      	@ApiResponse(responseCode = "401", description = "Lej??rt token",
	    content = { @Content(mediaType = "application/json") }),
      	@ApiResponse(responseCode = "302", description = "Nincs bejelentkezve, ??tir??ny??t??s a login oldalra",
	    content = { @Content(mediaType = "application/json") }),
      	@ApiResponse(responseCode = "400", description = "Nem megfelel?? k??r??s param??terek (valid??ci??s hiva)", 
	    content = { @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = ApiError.class)) }),
    	@ApiResponse(responseCode = "500", description = "Asztal nem l??tezik",
	    content = { @Content(mediaType = "application/json") })
    })
    @Operation(summary = "Asztal t??rl??se")
    @Caching(evict = {
        @CacheEvict(cacheNames = "table", key = "#pData.getName()"),
        @CacheEvict(cacheNames = "tables", key = "'getNotAvailableTables'"),
        @CacheEvict(cacheNames = "tables", key = "'getAvailableTables'")
    })    
    @DeleteMapping(path = "/delete/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(
        @Parameter(description = "Asztal neve", required = true, example = "A1") 
        @PathVariable(name = "name", required = true) 
        @NotEmpty(message = "error.table.name.notset")
        @NotBlank(message = "error.table.name.notset")
        @Size(min=2, max = 10, message = "error.table.name.short_or_long")
        @Pattern(regexp = "^[A-Z][a-zA-Z0-9]+$", message = "error.table.name.pattern_is_bad")        
                String pId) {
        Table tmp = tableService.findByName(pId);
        if(tmp!=null){
            tableService.delete(tmp);
        }
        else{
            throw new BusinessException("table.notexist");
        }
    }

    @Operation(summary = "K??pt??rl??se",
            security = {
            @SecurityRequirement(name = "apikey",scopes = {"file"}),
            @SecurityRequirement(name = "openid",scopes = {"file"}),
            @SecurityRequirement(name = "oauth2",scopes = {"file"}),
    })           
    @DeleteMapping(path = "/deleteimage/{fileid}", produces = MediaType.TEXT_PLAIN_VALUE)
    @AspectLogger
    public String deleteImageUrl(
        HttpServletRequest request,
        @PathVariable(name = "fileid") String pId) throws ApiException {            
            ImageControllerApi api = this.mediaClient.getClientInstanceWithToken(request.getHeader("Authorization"));
            return api.deleteFiles(pId, null).getId();
    }

    
        @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Sikeres k??pfelt??lt??s"),
        @ApiResponse(responseCode = "500", description = "K??pfelt??lt??s nem lehets??ges")    
    })
    @Operation(summary = "K??pfelt??lt??s",
            security = {
            @SecurityRequirement(name = "apikey",scopes = {"file"}),
            @SecurityRequirement(name = "openid",scopes = {"file"}),
            @SecurityRequirement(name = "oauth2",scopes = {"file"}),
    })       
    @PostMapping(value = "/addFiles", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
 
    public List<ImageProcessModel> addImageUrl(
            HttpServletRequest request,
            @RequestPart("files") MultipartFile[] files) throws IOException, ApiException {        
        ImageControllerApi api = this.mediaClient.getClientInstanceWithToken(request.getHeader("Authorization"));
        File f;
        List<ImageProcessModel> result = new ArrayList();
        
        
        for(MultipartFile f2: files){          
            f = Files.createTempFile(UUID.randomUUID().toString(), ".tmp").toFile();
            f.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(f);
            outputStream.write(f2.getBytes());
            outputStream.close();
            result.addAll(api.addFiles(f, null));
        }
        return result;
    }
    
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Sikeres m??velet", 
	    content = { @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = Table.class)) }),
	@ApiResponse(responseCode = "403", description = "Nincs megfelel?? jogosults??god",
	    content = { @Content(mediaType = "application/json") }),
      	@ApiResponse(responseCode = "401", description = "Lej??rt token",
	    content = { @Content(mediaType = "application/json") }),
      	@ApiResponse(responseCode = "302", description = "Nincs bejelentkezve, ??tir??ny??t??s a login oldalra",
	    content = { @Content(mediaType = "application/json") }),
      	@ApiResponse(responseCode = "400", description = "Nem megfelel?? k??r??s param??terek (valid??ci??s hiva)", 
	    content = { @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = ApiError.class)) }),
    	@ApiResponse(responseCode = "500", description = "Asztal nem l??tezik",
	    content = { @Content(mediaType = "application/json") })
    })
    @Operation(
                        security = {
            @SecurityRequirement(name = "apikey",scopes = {"table"}),
            @SecurityRequirement(name = "openid",scopes = {"table"}),
            @SecurityRequirement(name = "oauth2",scopes = {"table"}),
    },

            summary = "Asztal enged??lyez??se")
    @PutMapping(path = "/enable/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Caching(evict = {
        @CacheEvict(cacheNames = "tables", key = "'getNotAvailableTables'"),
        @CacheEvict(cacheNames = "tables", key = "'getAvailableTables'")
    })    
    public void enable(
        @Parameter(description = "Asztal neve", required = true, example = "A1")
                @NotEmpty(message = "error.table.name.notset")
        @NotBlank(message = "error.table.name.notset")
        @Size(min=2, max = 10, message = "error.table.name.short_or_long")
        @Pattern(regexp = "^[A-Z][a-zA-Z0-9]+$", message = "error.table.name.pattern_is_bad")
        @PathVariable(name = "name", required = true) String pId) {
        Table tmp = tableService.findByName(pId);
        if(tmp!=null){
            tmp.setAvailable(true);
            tableService.delete(tmp);
        }
        else{
            throw new BusinessException("table.notexist");
        }
    }

    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Sikeres m??velet", 
	    content = { @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = Table.class)) }),
	@ApiResponse(responseCode = "403", description = "Nincs megfelel?? jogosults??god",
	    content = { @Content(mediaType = "application/json") }),
      	@ApiResponse(responseCode = "401", description = "Lej??rt token",
	    content = { @Content(mediaType = "application/json") }),
      	@ApiResponse(responseCode = "302", description = "Nincs bejelentkezve, ??tir??ny??t??s a login oldalra",
	    content = { @Content(mediaType = "application/json") }),
      	@ApiResponse(responseCode = "400", description = "Nem megfelel?? k??r??s param??terek (valid??ci??s hiva)", 
	    content = { @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = ApiError.class)) }),
    	@ApiResponse(responseCode = "500", description = "Asztal m??r l??tezik",
	    content = { @Content(mediaType = "application/json") })
    })
    @Operation(
            security = {
            @SecurityRequirement(name = "apikey",scopes = {"table"}),
            @SecurityRequirement(name = "openid",scopes = {"table"}),
            @SecurityRequirement(name = "oauth2",scopes = {"table"}),
    },

        summary = "Asztal m??dos??t??sa")
    @Caching(evict = {
        @CacheEvict(cacheNames = "tables", key = "'getNotAvailableTables'"),
        @CacheEvict(cacheNames = "tables", key = "'getAvailableTables'")
    })    
    @PutMapping(path = "/disable/{name}", produces = MediaType.APPLICATION_JSON_VALUE)    
    public void disable(
        @Parameter(description = "Asztal neve", required = true)
                @NotEmpty(message = "error.table.name.notset")
        @NotBlank(message = "error.table.name.notset")
        @Size(min=2, max = 10, message = "error.table.name.short_or_long")
        @Pattern(regexp = "^[A-Z][a-zA-Z0-9]+$", message = "error.table.name.pattern_is_bad")
        @PathVariable(name = "name", required = true) String pId) {
        Table tmp = tableService.findByName(pId);
        if(tmp!=null){
            tmp.setAvailable(false);
            tableService.delete(tmp);
        }
        else{
            throw new BusinessException("table.notexist");
        }
    }

    
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Sikeres m??dos??t??s", 
	    content = { @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = Table.class)) }),
	@ApiResponse(responseCode = "403", description = "Nincs megfelel?? jogosults??god",
	    content = { @Content(mediaType = "application/json") }),
      	@ApiResponse(responseCode = "401", description = "Lej??rt token",
	    content = { @Content(mediaType = "application/json") }),
      	@ApiResponse(responseCode = "302", description = "Nincs bejelentkezve, ??tir??ny??t??s a login oldalra",
	    content = { @Content(mediaType = "application/json") }),
      	@ApiResponse(responseCode = "400", description = "Nem megfelel?? k??r??s param??terek (valid??ci??s hiva)", 
	    content = { @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = ApiError.class)) }),
    	@ApiResponse(responseCode = "500", description = "Asztal nem l??tezik",
	    content = { @Content(mediaType = "application/json") })
    })
    @Operation(
                        security = {
            @SecurityRequirement(name = "apikey",scopes = {"table"}),
            @SecurityRequirement(name = "openid",scopes = {"table"}),
            @SecurityRequirement(name = "oauth2",scopes = {"table"}),
    },

            summary = "Asztal m??dos??t??sa")
    @Caching(
            evict = {
                @CacheEvict(cacheNames = "tables", key = "'getNotAvailableTables'"),
                @CacheEvict(cacheNames = "tables", key = "'getAvailableTables'")
        },
            put = {
                @CachePut(cacheNames = "tablebyid", key = "#pData.getName()")
                    
            })    

    @PutMapping(path = "/modify", produces = MediaType.APPLICATION_JSON_VALUE)
    public Table modify(
            @Valid
        @Parameter(description = "Asztal", required = true) 
        @RequestBody(required = true) Table pTable) throws IllegalAccessException, InvocationTargetException {
        tableService.save(pTable);
        return pTable;
    }
    
}
