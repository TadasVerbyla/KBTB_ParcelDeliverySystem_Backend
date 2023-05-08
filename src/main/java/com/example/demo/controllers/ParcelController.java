package com.example.demo.controllers;

import com.example.demo.entities.Parcel;
import com.example.demo.services.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/V1/parcel")
public class ParcelController {

    private final ParcelService parcelService;

    @Autowired
    public ParcelController(ParcelService parcelService){
        this.parcelService = parcelService;
    }
    @GetMapping
    public List<Parcel> getParcels(){
        return parcelService.getParcels();
    }

    @PostMapping
    public void addNewParcel(@RequestBody Parcel parcel) {
        parcelService.addNewParcel(parcel);
    }

    @DeleteMapping(path = "{parcelId}")
    public void deleteParcel(@PathVariable("parcelId") Long parcelId) {
        parcelService.deleteParcel(parcelId);
    }

    @PutMapping(path = "{parcelId}")
    public void updateParcel(
            @PathVariable("parcelId") Long parcelId,
            @RequestParam(required = false) String deliveryAddress,
            @RequestParam(required = false) String recipient,
            @RequestParam(required = false) Integer weight) {
        parcelService.updateParcel(parcelId, deliveryAddress, recipient, weight);
    }
}
