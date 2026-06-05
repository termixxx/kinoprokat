package ru.vsu.kinoprokat.rental;

import jakarta.persistence.*;
import lombok.*;
import ru.vsu.kinoprokat.movie.MovieEntity;
import ru.vsu.kinoprokat.user.UserEntity;

import java.time.LocalDate;

@Entity
@Table(name = "rentals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_id")
    private MovieEntity movie;

    @Column(nullable = false)
    private LocalDate rentalDate;

}
