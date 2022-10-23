package se331.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.rest.entity.Event;
import se331.rest.entity.Organizer;
import se331.rest.service.OrganizerService;
import se331.rest.util.LabMapper;

@RestController
public class OrganizationController {
    @Autowired
    OrganizerService organizerService;


    @GetMapping("organizers")
    public ResponseEntity<?> getOrganizerLists(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page ){
        perPage = perPage == null ? 3 : perPage;
        page = page == null ? 1 : page;
        Page<Organizer> pageOutput;
        pageOutput = organizerService.getOrganizer(page, perPage);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        System.out.println(pageOutput);
        return new ResponseEntity<>(LabMapper.INSTANCE.getOrganizerDTO(pageOutput.getContent()), responseHeader, HttpStatus.OK);


    }
    //    @GetMapping("/organizers")
//    ResponseEntity<?> getOrganizers() {
//        return ResponseEntity.ok(LabMapper.INSTANCE.getOrganizerDTO(organizerService.getAllOrganizer()));
//    }
    @GetMapping("organizers/{id}")
    public ResponseEntity<?> getOrganizer(@PathVariable("id") Long id) {
        Organizer output = organizerService.getOrganizer(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getOrganizerDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
    @PostMapping("/organizers")
    public ResponseEntity<?> addOrganizer(@RequestBody Organizer organizer) {
        Organizer output = organizerService.save(organizer);
        return ResponseEntity.ok(LabMapper.INSTANCE.getOrganizerDTO(output));
    }
}

