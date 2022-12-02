package models

import java.time.LocalDateTime
import javax.enterprise.context.ApplicationScoped
import javax.persistence.*


@ApplicationScoped
@Entity
@Table(name="room_info")
data class RoomInfo(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val Id: Long?=0,
        var roomInfo: String?=null,
        var currentPrice: Double?=0.0,
        var discountPercentage: Number?=0,

        var createdAt: LocalDateTime?= LocalDateTime.now(),
        var updatedAt: LocalDateTime?= LocalDateTime.now()
) {
        @OneToMany(cascade = [CascadeType.ALL], targetEntity = RoomFacility::class)
        @JoinColumn(referencedColumnName = "Id", name = "room_id")
        var roomFacility: List<RoomFacility>?=null

        @ManyToOne
        var hotel: Hotel?=null

        @OneToOne
        var bookingInfo: BookingInfo?=null

        @OneToMany(cascade = [CascadeType.ALL], targetEntity = RoomAvailability::class)
        @JoinColumn(referencedColumnName = "Id", name = "room_id")
        var roomAvailability: List<RoomAvailability>?=null
}