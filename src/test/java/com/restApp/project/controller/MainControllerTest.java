package com.restApp.project.controller;

import com.restApp.project.entity.Cat;
import com.restApp.project.repository.CatRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MainControllerTest {

    @Mock
    private CatRepo catRepo;

    @InjectMocks
    private MainController controller;

    @Test
    void changeCatFailedTest() {
        int id = 1;
        Cat cat = new Cat();
        cat.setId(id);
        when(catRepo.existsById(id)).thenReturn(false);

        String expected = "No such row";

        assertEquals(expected, controller.changeCat(cat));
    }

    @Test
    void changeCatTest() {

        int id = 1;

        Cat cat = new Cat();

        cat.setId(id);
        cat.setName("Boris");

        when(catRepo.existsById(id)).thenReturn(true);
        when(catRepo.save(cat)).thenReturn(cat);

        assertEquals(cat.toString(), controller.changeCat(cat));
    }

}