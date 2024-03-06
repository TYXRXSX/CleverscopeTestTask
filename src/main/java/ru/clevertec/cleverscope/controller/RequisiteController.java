package ru.clevertec.cleverscope.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.cleverscope.dto.RequisiteDto;
import ru.clevertec.cleverscope.service.RequisiteService;

@RestController
@RequestMapping("api/requisites")
@RequiredArgsConstructor
public class RequisiteController {

    private final RequisiteService requisiteService;

    @GetMapping
    public Page<RequisiteDto> getRequisites(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size) {
        return requisiteService.getAllRequisites(PageRequest.of(page, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RequisiteDto> getRequisiteById(@PathVariable("id") Long id){
        return ResponseEntity.ok(requisiteService.getRequisiteById(id));
    }

    @PostMapping
    public ResponseEntity<RequisiteDto> postRequisite(@RequestBody @Valid RequisiteDto requisiteDto){
        return ResponseEntity.ok(requisiteService.saveRequisite(requisiteDto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteRequisite(@PathVariable("id") Long id){
        requisiteService.deleteRequisiteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<RequisiteDto> putRequisite(@RequestBody @Valid RequisiteDto requisiteDto){
        return ResponseEntity.ok(requisiteService.putRequisite(requisiteDto));
    }
}
