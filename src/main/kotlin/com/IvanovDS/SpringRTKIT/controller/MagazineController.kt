package com.IvanovDS.SpringRTKIT.controller

import com.IvanovDS.SpringRTKIT.model.Magazine
import com.IvanovDS.SpringRTKIT.service.MagazineService
import org.springframework.web.bind.annotation.RestController

@RestController
class MagazineController(private val magazineService: MagazineService) {
}