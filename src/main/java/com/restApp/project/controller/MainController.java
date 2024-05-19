package com.restApp.project.controller;

import com.restApp.project.DTO.CatDTO;
import com.restApp.project.entity.Cat;
import com.restApp.project.repository.CatRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "MainMethods")
@RestController
@RequiredArgsConstructor
public class MainController {

    private final CatRepo catRepo;

    @Operation(
            summary = "Кладет нового котика в базу",
            description = "Получает DTO кота и через билдер собирает и записывает сущность в бд"
    )
    @PostMapping("/api/add")
    public Cat addCat(@RequestBody CatDTO catDTO) {
       return catRepo.save(
                        Cat.builder()
                            .age(catDTO.getAge())
                            .name(catDTO.getName())
                            .weight(catDTO.getWeight())
                            .build()
       );
    }

    @Operation(
            summary = "Выдает список всех котеек",
            description = "Выдает всех котеек"
    )
    @SneakyThrows
    @GetMapping("/api/all")
    public List<Cat> getAll() {
        return catRepo.findAll();
    }

    @Operation(
            summary = "Выдает котейку",
            description = "Выдает информацию про котейку при вызове, например, по id"
    )
    @GetMapping("/api")
    public Cat getCat(@RequestParam int id) {
        return catRepo.findById(id).orElseThrow();
    }

    @Operation(
            summary = "Удаляет котейку",
            description = "Удаляет информацию по котейке"
    )
    @DeleteMapping("/api")
    public void deleteCat(@RequestParam int id) {
        catRepo.deleteById(id);
    }

    @Operation(
            summary = "Добавляет котейку",
            description = "Добавляет информацию по котейке"
    )
    @PutMapping("/api/add")
    public String changeCat(@RequestBody Cat cat) {
        if (!catRepo.existsById(cat.getId())) {
            return "No such row";
        }
        return catRepo.save(cat).toString();
    }
}
