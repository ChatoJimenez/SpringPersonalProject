package com.example.springpostgres.web;

import com.example.springpostgres.model.Array;
import com.example.springpostgres.model.Message;
import com.example.springpostgres.service.ArrayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping(path="/array")
public class ArrayController {

    @Autowired
    private final ArrayService arrayService;

    public ArrayController(ArrayService arrayService) {
        this.arrayService = arrayService;
    }

    @GetMapping("/duplicates/{id}")
    public String getDuplicates(@PathVariable int id){
        return "Id: "+ arrayService.getArrayById(id).getId() +"\nName: "+ arrayService.getArrayById(id).getName()
                +"\nData: "+arrayService.getAll(id) + "\nDuplicates: " + arrayService.getDuplicates(id);
    }

    @GetMapping("/largest/{id}")
    public String getLargest(@PathVariable int id){
        return "Id: "+ arrayService.getArrayById(id).getId() +"\nName: "+ arrayService.getArrayById(id).getName()
                +"\nData: "+arrayService.getAll(id)+ "\nLargest element: " + arrayService.getLargestElement(id);
    }

    @GetMapping("/first/{id}")
    public String getFirst(@PathVariable int id){
        return "Id: "+ arrayService.getArrayById(id).getId() +"\nName: "+ arrayService.getArrayById(id).getName()
                +"\nData: "+arrayService.getAll(id)+ "\nFirst element: " + arrayService.getFirstElement(id);
    }

    //CRUD
//    @GetMapping("/arrays")
//    public List<Array> getArrays (){
//        return arrayService.getAllArrays();
//    }

    @GetMapping("/arrays")
    public ResponseEntity<List<Array>> getArrays (){
        return new ResponseEntity<>(arrayService.getAllArrays(), HttpStatus.OK);
    }


//    @GetMapping("/arrays/{id}")
//    public Array getArray(@PathVariable int id){
//        return arrayService.getArrayById(id);
//    }

    @GetMapping("/arrays/{id}")
    public ResponseEntity<Array> getArray(@PathVariable int id){
        return new ResponseEntity<>( arrayService.getArrayById(id), HttpStatus.OK);
    }


//    @PostMapping("/arrays")
//    public Array createArray(@RequestBody Array arr) {
//        return arrayService.createArray(arr);
//    }

//    @PostMapping("/arrays")
//    public ResponseEntity<Array> createArray(@RequestBody Array arr) {
//        return new ResponseEntity<>(arrayService.createArray(arr), HttpStatus.CREATED);
//    }

    @PostMapping("/arrays")
    public ResponseEntity<Message> createArray(@RequestBody Array arr) {
        arrayService.createArray(arr);
        return new ResponseEntity<>(new Message(
                "The array with id: " + arr.getId() +" was created successfully",
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED,
                ZonedDateTime.now()),
                HttpStatus.CREATED);
    }


//    @PutMapping("/arrays/{id}")
//    public Array updateArray(@PathVariable int id, @RequestBody Array arr){
//        return arrayService.updateArray(id, arr);
//    }

//    @PutMapping("/arrays/{id}")
//    public ResponseEntity<Array> updateArray(@PathVariable int id, @RequestBody Array arr){
//        return new ResponseEntity<>(arrayService.updateArray(id, arr), HttpStatus.OK);
//    }

    @PutMapping("/arrays/{id}")
    public ResponseEntity<Message> updateArray(@PathVariable int id, @RequestBody Array arr){
        arrayService.updateArray(id, arr);
        return new ResponseEntity<>(new Message(
                "The array with id: " + id + " was updated successfully",
                HttpStatus.OK.value(),
                HttpStatus.OK,
                ZonedDateTime.now()),
                HttpStatus.OK);
    }

//    @DeleteMapping("/arrays/{id}")
//    public void deleteArray(@PathVariable int id) {
//        arrayService.deleteArray(id);
//    }

//    @DeleteMapping("/arrays/{id}")
//    public ResponseEntity<String> deleteArray(@PathVariable int id) {
//        arrayService.deleteArray(id);
//        return new ResponseEntity<>("Deleted",HttpStatus.OK);
//    }

    @DeleteMapping("/arrays/{id}")
    public ResponseEntity<Message> deleteArray(@PathVariable int id) {
        arrayService.deleteArray(id);
        return new ResponseEntity<>(new Message(
                "The array with id: " + id + " was deleted successfully",
                HttpStatus.OK.value(),
                HttpStatus.OK,
                ZonedDateTime.now()),
                HttpStatus.OK);
    }

}
