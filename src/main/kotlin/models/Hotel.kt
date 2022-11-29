package models

import java.time.LocalDateTime
import javax.enterprise.context.ApplicationScoped
import javax.persistence.*

@ApplicationScoped
@Entity
@Table(name="hotel")
data class Hotel(
        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        val id: Long?=0,
        var name: String?=null,
        var imageHotel: String?=null,
        var description: String?=null,

        var createdAt: LocalDateTime?= LocalDateTime.now(),
        var updatedAt: LocalDateTime?= LocalDateTime.now()
) {
        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(referencedColumnName = "id", name = "hotel_id")
        var hotelService: HotelService?=null

        @OneToMany(cascade = [CascadeType.ALL], targetEntity = RoomInfo::class)
        @JoinColumn(referencedColumnName = "id", name = "hotel_id")
        var roomInfo: List<RoomInfo>?=null
}
