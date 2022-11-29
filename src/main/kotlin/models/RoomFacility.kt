package models

import java.time.LocalDateTime
import javax.enterprise.context.ApplicationScoped
import javax.persistence.*

@ApplicationScoped
@Entity
@Table(name = "room_facility")
data class RoomFacility(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val Id: Long?=0,

        var createdAt: LocalDateTime?= LocalDateTime.now(),
        var updatedAt: LocalDateTime?= LocalDateTime.now()
) {
        @ManyToOne
        var roomInfo: RoomInfo?=null

        @ManyToOne
        var amenity: Amenity?=null
}
