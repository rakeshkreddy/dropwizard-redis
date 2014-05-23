package org.sample.resource;

import io.dropwizard.jersey.params.LongParam;
import org.sample.model.User;
import org.sample.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

/**
 * Created by Rakesh Komulwad on 5/23/2014.
 */

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Component
public class UserResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

    @Autowired
    private
    UserRepository repository;

    @GET
    @Path("{userId}")
    public User fetch(@PathParam("userId") Long userId) {
        User user = repository.get(userId);
        if (user == null) {
            LOGGER.error("User not found "+userId);
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return user;
    }

    @POST
    public Response add(@Valid User user) {
        final long id = System.currentTimeMillis();
        user.setUserId(id);
        repository.add(user);

        LOGGER.info("Saved user "+user);

        return Response.created(UriBuilder.fromResource(UserResource.class)
                .segment("{id}").build(id))
                .build();
    }

    public UserRepository getRepository() {
        return repository;
    }

    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }
}
