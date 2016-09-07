package com.ellepsis.solanteqTest.controller.restController;

import com.ellepsis.solanteqTest.entity.Position;
import com.ellepsis.solanteqTest.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ellepsis on 07.09.2016.
 */
@RestController
@RequestMapping(path = "/api/position/")
public class PositionController {

    private final int MAX_POSITION_NAME_LENGTH = 100;

    private PositionRepository positionRepository;

    @RequestMapping(path = "getPositions/", method = RequestMethod.GET)
    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    @RequestMapping(path = "addNewPosition/", method = RequestMethod.POST)
    public void addNewPosition(@RequestBody Position position){
        if (position == null || position.getPositionName().isEmpty())
            throw new IllegalArgumentException("Position must be not null or empty");
        if (position.getPositionName().length() >= MAX_POSITION_NAME_LENGTH)
            throw  new IllegalArgumentException("Position name length is bigger than " + MAX_POSITION_NAME_LENGTH + " symbols");
        positionRepository.save(position);
    }

    @Autowired
    public void setPositionRepository(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }
}
