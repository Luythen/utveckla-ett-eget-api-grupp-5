package org.acme.resource;

import java.nio.file.AccessDeniedException;

import org.acme.SwaggerMessages.SwaggerDocs;
import org.acme.config.ApiKeyFilter;
import org.acme.model.HeroDto;
import org.acme.model.HeroResponseDto;
import org.acme.service.HeroService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;

@Path("/api/hero")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HeroResource {

    @Inject
    ResourceHelper res;

    @Inject
    HeroService heroService;

    @Inject
    EntityManager em;

    @Inject
    ApiKeyFilter apiKeyFilter;

    @POST
    @Transactional
    @Operation(summary = "Creates a new hero", description = "Creates a new hero in the system, check Example Value for structure.")
    @APIResponse(responseCode = "200", description = SwaggerDocs.CREATE_NEW_HERO_STRING)
    @Path("/new-hero")
    public Response newHero(HeroDto heroDto) {

        // Kontrollera om hero är null, vilket kan hända om användaren inte skickar data
        // eller skickar fel format
        if (heroDto == null) {
            return res.respond("Invalid hero data.", "bad");
        }

        // Kontrollera om hero namn finns.
        if (heroDto.getName() == null || heroDto.getName().isEmpty()) {
            return res.respond("Hero name is required.", "bad");
        }

        // Kontrollera om hero race finns.
        if (heroDto.getRace() == null || heroDto.getRace().isEmpty()) {
            return res.respond("Hero race is required.", "bad");
        }

        // kontrollera om hero class finns.
        if (heroDto.getHeroClass() == null || heroDto.getHeroClass().isEmpty()) {
            return res.respond("Hero class is required.", "bad");
        }

        try {
            HeroResponseDto hero = heroService.createHero(heroDto);
            return res.respond(hero);

        } catch (IllegalArgumentException e) {
            return res.respond(e, "Error creating hero.");

        } catch (AccessDeniedException e) {
            return res.respond(e);
        }

    }

    @GET
    @APIResponse(responseCode = "JAVASCRIPT EXAMPLE", description = SwaggerDocs.HERO_FETCH_ALL_HEROES_JAVASCRIPT_STRING)
    @Path("/get-user-heroes")
    public Response getUserHeroes() {
        try {
            return res.respond(heroService.getHeroes());
        } catch (NoResultException e) {
            return res.respond(e);
        }
    }

    @GET
    @Path("/get-all-heroes")
    public Response getAllHeroes() {

        try {
            return res.respond(heroService.getAllHeroesResponse());
        } catch (NoResultException e) {
            return res.respond(e);
        }
    }

    @GET
    @APIResponse(responseCode = "JAVASCRIPT EXAMPLE", description = SwaggerDocs.HERO_FETCH_HERO_BY_NAME_JAVASCRIPT_STRING)
    @Path("/get-hero-by-name/{heroName}")
    public Response getHeroByName(@PathParam("heroName") String heroName) {

        // Kontrollera om hero namn blev skrivet

        if (heroName == null || heroName.isEmpty()) {
            return res.respond("Hero name is required", "bad");
        }
        try {
            HeroResponseDto responseDto = heroService.getHeroByName(heroName);
            return res.respond(responseDto);

        } catch (IllegalArgumentException e) {
            return res.respond(e);

        } catch (AccessDeniedException e) {
            return res.respond(e);

        }
    }

    @GET
    @Path("/get-heroes-by-race/{race}")
    public Response getHeroesByRace(@PathParam("race") String race) {
        try {
            return res.respond(heroService.getHeroesByRace(race));

        } catch (NotFoundException e) {
            return res.respond(e);

        } catch (IllegalArgumentException e) {
            return res.respond(e);

        }
    }

    // Uppdaterar en hero baserat på id
    @PATCH
    @Path("/update-hero")
    @Transactional
    public Response updateHero(HeroDto heroDto) {

        // kontrollera att data har skickats.
        if (heroDto == null) {

            return res.respond("No hero data provided.", "bad");
        }

        // Kontrollera att namn finns.
        if (heroDto.getName() == null || heroDto.getName().isEmpty()) {

            return res.respond("Hero name is required.", "bad");
        }

        // Anropa service för att uppdatera hero
        try {
            HeroResponseDto hero = heroService.updateHero(heroDto);

            // Om hero inte finns, returnera 404
            if (hero == null) {
                return res.respond("Hero not Found", "notFound");
            }
            // Returnera uppdaterad hero
            return res.respond(hero);

        } catch (AccessDeniedException e) {
            return res.respond(e);

        } catch (NoResultException e) {
            return res.respond(e);

        } catch (IllegalArgumentException e) {
            return res.respond(e);

        }
    }

    @GET
    @Path("/get-hero-by-weapon/{weapon}")
    public Response getHeroesByWeapon(@PathParam("weapon") String weapon) {

        try {
            return res.respond(heroService.getHeroesByWeapon(weapon));
        } catch (NotFoundException e) {
            return res.respond(e);
        } catch (IllegalArgumentException e) {
            return res.respond(e);
        }
    }

    @GET
    @Path("/get-hero-by-id/{id}")
    public Response getHeroById(@PathParam("id") int id) {

        try {
            return res.respond(heroService.getHeroResponseById(id));

        } catch (NotFoundException e) {
            return res.respond(e);
        } catch (IllegalArgumentException e) {
            return res.respond(e);
        }

    }

    // Raderar en hero baserat på id
    @DELETE
    @APIResponse(responseCode = "JAVASCRIPT EXAMPLE", description = SwaggerDocs.HERO_DELETE_HERO_JAVASCRIPT_STRING)
    @Path("/{id}")
    @Transactional
    public Response deleteHero(@PathParam("id") int id) {

        try {
            boolean deleted = heroService.deleteHero(id);

            // om hero inte finns, returnera 404
            if (!deleted) {
                return res.respond("Hero not found", "notFound");
            }

            // Om hero har tagits bort, returnera 204 No Content
            return res.respond("Hero has been removed.");

        } catch (AccessDeniedException e) {
            return res.respond(e);

        } catch (NullPointerException e) {
            return res.respond(e);

        } catch (NoResultException e) {
            return res.respond(e);

        }
    }

    // Exempel: /api/hero/get-hero-by-class/MAGE
    // Detta visar alla hjältar som har klassen MAGE
    // det går att byta ut MAGE mot någon av de andra klasserna: WARRIOR, ROGUE,
    // PALADIN
    // för att visa hjältar av den klassen istället.

    @GET
    @Path("/get-hero-by-class/{heroClass}")
    public Response getHeroesByClass(@PathParam("heroClass") String heroClass) {

        try {
            return res.respond(heroService.getHeroesByClass(heroClass));
        } catch (NotFoundException e) {
            return res.respond(e);
        } catch (IllegalArgumentException e) {
            return res.respond(e);
        }

    }

}
