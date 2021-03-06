package com.webdev.productsystem.Tours.City.Infrastructure.Controllers;

import com.webdev.productsystem.Tours.City.Application.Create.CreateCity;
import com.webdev.productsystem.Tours.Hotel.Application.Create.HotelCreator;
import com.webdev.productsystem.Tours.Hotel.Domain.Exceptions.HotelAlreadyExists;
import com.webdev.productsystem.Tours.Hotel.Domain.Exceptions.HotelNameInvalidLength;
import com.webdev.productsystem.Tours.Hotel.Domain.Exceptions.HotelNotExists;
import com.webdev.productsystem.Tours.Tour.Application.Create.CreateTour;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@Tag(name = "City", description = "City REST API")
@RequestMapping(value = "/city")
public class CityCreateController {
    @Autowired
    private CreateCity creator;

    @PostMapping(value = "/create")
    @CrossOrigin("*")
    public ResponseEntity execute(@RequestBody CityCreatorRequest request) {
        creator.execute(request.getId(), request.getName(), request.getCountry(),request.getTouristicLocationId());
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    static class CityCreatorRequest {
        @Schema(description = "City id", example = "001")
        private String id;
        @Schema(description = "City name", example = "Santa Marta")
        private String name;
        @Schema(description = "City country", example = "Colombia")
        private String country;
        @Schema(description = "City touristicLocationId", example = "bd8629e1-55b2-498c-99f5-234b280846ef")
        private String touristicLocationId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setDate(String date) {
            this.country = date;
        }
        public String getCountry() {
            return country;
        }

        public void setCountry(String name) {
            this.name = name;
        }

        public String getTouristicLocationId() {
            return touristicLocationId;
        }

        public void setTouristicLocationId(String touristicLocationId) {
            this.touristicLocationId = touristicLocationId;
        }


    }
}
