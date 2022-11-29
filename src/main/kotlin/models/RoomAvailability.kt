package models

import java.time.LocalDateTime
import javax.enterprise.context.ApplicationScoped
import javax.persistence.*

@ApplicationScoped
@Entity
@Table(name = "room_availability")
data class RoomAvailability(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val Id: Long?=0,
        var fromDate: LocalDateTime?=null,
        var toDate: LocalDateTime?=null,
        var numOfRooms: Number?=0
) {
        @ManyToOne
        var roomInfo: RoomInfo?=null
}
