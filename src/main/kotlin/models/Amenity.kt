package models

import java.time.LocalDateTime
import javax.enterprise.context.ApplicationScoped
import javax.persistence.*


@ApplicationScoped
@Entity
@Table(name="amenity")
public data class Amenity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val Id: Long?=0,
        var info: String?=null,
        var displayImage: String?=null,

        var createdAt: LocalDateTime?=LocalDateTime.now(),
        var updatedAt: LocalDateTime?= LocalDateTime.now()

        ) {
        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(referencedColumnName = "Id", name = "amenity_id")
        var hotelService: HotelService?=null

        @OneToMany(cascade = [CascadeType.ALL], targetEntity = RoomFacility::class)
        @JoinColumn(referencedColumnName = "Id", name = "amenity_id")
        var roomFacility: List<RoomFacility>?=null
}
