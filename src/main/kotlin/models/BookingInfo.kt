package models

import enums.BookingStatus
import java.time.LocalDateTime
import javax.enterprise.context.ApplicationScoped
import javax.persistence.*

@ApplicationScoped
@Entity
@Table(name = "booking_info")
data class BookingInfo(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val Id:Long?=0,
        var fromDate: LocalDateTime?=null,
        var toDate: LocalDateTime?=null,
        @Enumerated(EnumType.STRING)
        var bookingStatus: BookingStatus?=null,
        var user_id: String?=null,

        var createdAt: LocalDateTime?=LocalDateTime.now(),
        var updatedAt: LocalDateTime?= LocalDateTime.now()
) {
        @OneToOne
        var roomInfo: RoomInfo?=null
}
