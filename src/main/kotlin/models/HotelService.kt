package models

import java.time.LocalDateTime
import javax.enterprise.context.ApplicationScoped
import javax.persistence.*

@ApplicationScoped
@Entity
@Table(name="hotel_service")
data class HotelService(
        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        val id: Long?=0,
        var description: String?=null,

        var createdAt: LocalDateTime?= LocalDateTime.now(),
        var updatedAt: LocalDateTime?= LocalDateTime.now()
) {
        @OneToOne
        var hotel: Hotel?=null

        @OneToOne
        var amenity: Amenity?=null
}
