package controllers

import data.HotelDTO
import data.RoomDTO
import io.smallrye.mutiny.Uni
import services.HotelService
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("hotel/")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class HotelResource {

    @Inject
    lateinit var hotelService: HotelService

    @Path("/create")
    @POST
    fun saveHotel(hotel: HotelDTO): Response {
        val response = hotelService.createHotel(hotel)
        return Response.ok(response).build()
    }

    @Path("/{hotel_id}")
    @GET
    fun getHotel(@PathParam("hotel_id") Id: Long): Uni<Response> {
        return hotelService.getHotel(Id)
    }

    @Path("update/{hotel_id}")
    @PUT
    fun updateHotel(@PathParam("hotel_id") Id: Long, hotel: HotelDTO): Uni<Response>? {
        return hotelService.updateHotel(Id, hotel)
    }

    @Path("room/create/{hotel_id}")
    @POST
    fun saveRoom(@PathParam("hotel_id") Id: Long, room: RoomDTO): Uni<Uni<Response>>? {
        return hotelService.createRoom(Id, room)
    }

}