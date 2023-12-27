package com.ning.aluaback.controller;

import com.ning.aluaback.dto.R;
import com.ning.aluaback.dto.manage.ManageRequest;
import com.ning.aluaback.service.ManageService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Path;

/**
 * @Project: com.ning.aluaback.controller
 * @Author: pgthinker
 * @Date: 2023/12/27 14:02
 * @Description:
 */
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class ManageController {
    private final ManageService manageService;
    @PostMapping("/user/ban")
    public R banAccount(@RequestBody ManageRequest manageRequest){
        return manageService.banAccount(manageRequest);
    }
    @GetMapping("/user")
    public R userPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit){
        return manageService.userInfoPage(page,limit);
    }
    @DeleteMapping("/user/delete/{username}")
    public R deleteAccount(@PathVariable String username){
        return manageService.deleteAccount(username);
    }

}
