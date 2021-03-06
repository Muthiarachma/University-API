package com.example.universitas.controller;

        import java.util.Arrays;
        import java.util.HashMap;
        import java.util.Map;
        import javax.annotation.security.RolesAllowed;
        import javax.ws.rs.core.Response;

        import com.example.universitas.model.dto.AdminDTO;
        import com.example.universitas.model.dto.UserDTO;
        import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
        import org.keycloak.OAuth2Constants;
        import org.keycloak.admin.client.CreatedResponseUtil;
        import org.keycloak.admin.client.Keycloak;
        import org.keycloak.admin.client.KeycloakBuilder;
        import org.keycloak.admin.client.resource.RealmResource;
        import org.keycloak.admin.client.resource.UserResource;
        import org.keycloak.admin.client.resource.UsersResource;
        import org.keycloak.authorization.client.AuthzClient;
        import org.keycloak.authorization.client.Configuration;
        import org.keycloak.representations.AccessTokenResponse;
        import org.keycloak.representations.idm.CredentialRepresentation;
        import org.keycloak.representations.idm.RoleRepresentation;
        import org.keycloak.representations.idm.UserRepresentation;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;


@RequestMapping(value = "/akun")
@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private String authServerUrl = "http://localhost:8080/auth";
    private String realm = "university-api";
    private String clientId = "university-api";
//    private String role = "app-user";
    //Get client secret from the Keycloak admin console (in the credential tab)
    private String clientSecret = "7804a8e3-2413-4db1-8bec-32c95cb6fe71";

    @PostMapping(path = "/createUser")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {

        Keycloak keycloak = KeycloakBuilder.builder().serverUrl(authServerUrl)
                .grantType(OAuth2Constants.PASSWORD).realm("master").clientId("admin-cli")
                .username("admin").password("root")
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()).build();

        keycloak.tokenManager().getAccessToken();


        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstname());
        user.setLastName(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());

        // Get realm
        RealmResource realmResource = keycloak.realm(realm);
        UsersResource usersRessource = realmResource.users();

        Response response = usersRessource.create(user);

        userDTO.setStatusCode(response.getStatus());
        userDTO.setStatus(response.getStatusInfo().toString());

        if (response.getStatus() == 201) {

            String userId = CreatedResponseUtil.getCreatedId(response);

            log.info("Created userId {}", userId);


            // create password credential
            CredentialRepresentation passwordCred = new CredentialRepresentation();
            passwordCred.setTemporary(false);
            passwordCred.setType(CredentialRepresentation.PASSWORD);
            passwordCred.setValue(userDTO.getPassword());

            UserResource userResource = usersRessource.get(userId);

            // Set password credential
            userResource.resetPassword(passwordCred);

            // Get realm role student
            RoleRepresentation realmRoleUser = realmResource.roles().get("app-user").toRepresentation();

            // Assign realm role student to user
            userResource.roles().realmLevel().add(Arrays.asList(realmRoleUser));
        }
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping(path = "/createAdmin")
    public ResponseEntity<?> createAdmin(@RequestBody AdminDTO adminDTO) {

        Keycloak keycloak = KeycloakBuilder.builder().serverUrl(authServerUrl)
                .grantType(OAuth2Constants.PASSWORD).realm("master").clientId("admin-cli")
                .username("admin").password("root")
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()).build();

        keycloak.tokenManager().getAccessToken();


        UserRepresentation admin = new UserRepresentation();
        admin.setEnabled(true);
        admin.setUsername(adminDTO.getEmail());
        admin.setFirstName(adminDTO.getFirstname());
        admin.setLastName(adminDTO.getLastname());
        admin.setEmail(adminDTO.getEmail());

        // Get realm
        RealmResource realmResource = keycloak.realm(realm);
        UsersResource usersRessource = realmResource.users();

        Response response = usersRessource.create(admin);

        adminDTO.setStatusCode(response.getStatus());
        adminDTO.setStatus(response.getStatusInfo().toString());

        if (response.getStatus() == 201) {

            String userId = CreatedResponseUtil.getCreatedId(response);

            log.info("Created userId {}", userId);


            // create password credential
            CredentialRepresentation passwordCred = new CredentialRepresentation();
            passwordCred.setTemporary(false);
            passwordCred.setType(CredentialRepresentation.PASSWORD);
            passwordCred.setValue(adminDTO.getPassword());

            UserResource userResource = usersRessource.get(userId);

            // Set password credential
            userResource.resetPassword(passwordCred);

            // Get realm role student
            RoleRepresentation realmRoleUser = realmResource.roles().get("app-admin").toRepresentation();

            // Assign realm role student to user
            userResource.roles().realmLevel().add(Arrays.asList(realmRoleUser));
        }
        return ResponseEntity.ok(adminDTO);
    }

    @PostMapping(path = "/signinUser")
    public ResponseEntity<?> signinUser (@RequestBody  UserDTO userDTO) {

        Map<String, Object> clientCredentials = new HashMap<>();
        clientCredentials.put("secret", clientSecret);
        clientCredentials.put("grant_type", "password");

        Configuration configuration =
                new Configuration(authServerUrl, realm, clientId, clientCredentials, null);
        AuthzClient authzClient = AuthzClient.create(configuration);

        AccessTokenResponse response =
                authzClient.obtainAccessToken(userDTO.getEmail(), userDTO.getPassword());

        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/signinAdmin")
    public ResponseEntity<?> signinAdmin (@RequestBody  AdminDTO adminDTO) {

        Map<String, Object> clientCredentials = new HashMap<>();
        clientCredentials.put("secret", clientSecret);
        clientCredentials.put("grant_type", "password");

        Configuration configuration =
                new Configuration(authServerUrl, realm, clientId, clientCredentials, null);
        AuthzClient authzClient = AuthzClient.create(configuration);

        AccessTokenResponse response =
                authzClient.obtainAccessToken(adminDTO.getEmail(), adminDTO.getPassword());

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/unprotected-data")
    public String getName() {
        return "Hello, this api is not protected.";
    }


    @RolesAllowed({"admin", "user"})
    @GetMapping(value = "/protected-data")
    public String getEmail() {
        return "Hello, this api is protected.";
    }

}