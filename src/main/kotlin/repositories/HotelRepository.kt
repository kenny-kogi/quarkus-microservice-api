package repositories

import models.Hotel
import models.RoomInfo
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class HotelRepository: PanacheRepository<Hotel> {
}

@ApplicationScoped
class roomRepository: PanacheRepository<RoomInfo> {
}