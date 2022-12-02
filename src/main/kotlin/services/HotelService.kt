package services

import data.APIResponse
import data.HotelDTO
import data.RoomDTO
import io.smallrye.mutiny.Uni
import models.Hotel
import models.RoomInfo
import repositories.HotelRepository
import repositories.roomRepository
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.transaction.Transactional
import javax.ws.rs.core.Response

@ApplicationScoped
@Transactional
class HotelService {
    @Inject
    lateinit var hotelRepository: HotelRepository

    @Inject
    lateinit var roomRepository: roomRepository

    fun createHotel(hotel: HotelDTO): Uni<Response>? {
        return hotelRepository.persist(Hotel(
                name = hotel.name,
                imageHotel = hotel.imageHotel,
                description = hotel.description
        )).onItem().transform { hotel ->
            Response
                    .status(Response.Status.OK)
                    .entity(APIResponse(Response.Status.CREATED.statusCode, "Created Successfully", hotel)).build()
        }
    }

    fun getHotel(Id: Long): Uni<Response> {
       return  hotelRepository.findById(Id).onItem().ifNotNull().transform {hotel ->
              Response.status(Response.Status.OK).entity(
                      APIResponse(
                              Response.Status.OK.statusCode,
                              "Successfully",
                              HotelDTO(
                                      name = hotel.name,
                                      imageHotel = hotel.imageHotel,
                                      description = hotel.description
                              )
                      )
            ).build()
        }.replaceIfNullWith(
               Response.status(Response.Status.NOT_FOUND.statusCode).entity(APIResponse(
                       Response.Status.NOT_FOUND.statusCode,
                       "Hotel Not Found",
                       null
               )).build()
        )
    }

    fun updateHotel(Id: Long, hotel: HotelDTO): Uni<Response>? {
        return hotelRepository.findById(Id).onItem().ifNotNull().transform { hotel ->
                hotel.name = hotel.name
                hotel.imageHotel = hotel.imageHotel
                hotel.description = hotel.description
                hotelRepository.persist(hotel)

                Response.status(Response.Status.OK).entity(
                        APIResponse(
                                Response.Status.OK.statusCode,
                                "Updated Successfully",
                                HotelDTO(
                                        name = hotel.name,
                                        imageHotel = hotel.imageHotel,
                                        description = hotel.description
                                )
                        )
                ).build()
        }.replaceIfNullWith(
                Response.status(Response.Status.OK).entity(
                        APIResponse(
                                Response.Status.OK.statusCode,
                                "Hotel Not Found",
                                null
                        )
                ).build()
        )
    }

    fun createRoom(hotelId: Long, room: RoomDTO): Uni<Uni<Uni<RoomInfo>>>? {
       return hotelRepository.findById(hotelId).onItem().ifNotNull().transform { hotel ->
            roomRepository.persist(RoomInfo(
                    roomInfo = room.roomInfo,
                    currentPrice = room.currentPrice,
                    discountPercentage = room.discountPercentage
            )).onItem().transform { room ->
                room.hotel= hotel
                roomRepository.persist(room)
            }.invoke { room ->
                Response.status(Response.Status.OK).entity(
                        APIResponse(
                                Response.Status.OK.statusCode,
                                "Updated Successfully",
                                null
                        )
                ).build()
            }
        }
    }
}