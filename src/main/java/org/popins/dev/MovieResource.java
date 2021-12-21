package org.popins.dev;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.popins.dev.model.Movie;


@Path("/movies")
public class MovieResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMovies() {
		List<Movie> movieList = Movie.listAll();
		return Response.ok(movieList).build();
	}
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMovieById(@PathParam("id") Long id) {
		return Movie.findByIdOptional(id)
		     .map(movie -> Response.ok(movie).build())
		     .orElse(Response.status(Response.Status.NOT_FOUND).build());
	}
	
	@GET
	@Path("country/{country}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMovieByCountry(@PathParam("country") String country) {
		List<Movie> movies = Movie.list("SELECT m FROM MOVIE m WHERE m.country = ?1 ORDER BY id DESC", country);
		return Response.ok(movies).build();
	}
	
	@GET
	@Path("title/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMovieByTitle(@PathParam("title") String title) {
		return Movie.find("title", title)
		     .singleResultOptional()
		     .map(movie -> Response.ok(movie).build())
		     .orElse(Response.status(Response.Status.NOT_FOUND).build());
	}
	
	@POST
	@Transactional
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response persistMovie(Movie movie) {
		Movie.persist(movie);
		return movie.isPersistent() ? Response.created(URI.create("/movies/"+movie.id)).build()
				                    : Response.status(Response.Status.BAD_REQUEST).build();
	}
	
	@DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deleteMovieBydId(@PathParam("id") Long id) {
        boolean deleted = Movie.deleteById(id);
        if(deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
