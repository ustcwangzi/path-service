package com.path.server.controller;

import com.path.server.dto.AddPathDTO;
import com.path.server.service.PathService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/server/path")
public class PathController {

    @Resource
    private PathService pathService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public boolean add(@RequestBody AddPathDTO request) {
        return pathService.addPath(request);
    }

    @ResponseBody
    @RequestMapping(value = "/query")
    public Map<String, List<String>> query(Integer sourceCity) {
        return pathService.query(sourceCity);
    }
}
