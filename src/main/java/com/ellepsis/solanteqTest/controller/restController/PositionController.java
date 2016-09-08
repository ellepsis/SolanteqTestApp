package com.ellepsis.solanteqTest.controller.restController;

import com.ellepsis.solanteqTest.entity.Position;
import com.ellepsis.solanteqTest.exception.InvalidDataException;
import com.ellepsis.solanteqTest.repository.PositionRepository;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Ellepsis on 07.09.2016.
 */
@RestController
@RequestMapping(path = "/api/position/")
public class PositionController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new ISO8601DateFormat();
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    private final int MAX_POSITION_NAME_LENGTH = 100;

    private PositionRepository positionRepository;

    @RequestMapping(path = "getPositions/", method = RequestMethod.GET)
    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    @RequestMapping(path = "addNewPosition/", method = RequestMethod.POST)
    public void addNewPosition(@RequestBody Position position){
        if (position == null || position.getPositionName().isEmpty())
            throw new InvalidDataException("Position must be not null or empty");
        if (position.getPositionName().length() >= MAX_POSITION_NAME_LENGTH)
            throw new InvalidDataException("Position name length is bigger than " + MAX_POSITION_NAME_LENGTH + " symbols");
        positionRepository.save(position);
    }

    @Autowired
    public void setPositionRepository(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }
}
