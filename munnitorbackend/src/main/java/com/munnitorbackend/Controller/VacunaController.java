package com.munnitorbackend.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://www.rebichada.com.ar:8080")
@RequestMapping("/vacuna")
public class VacunaController {
}
