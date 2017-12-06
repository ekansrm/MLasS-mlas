package com.ekansrm.mlas.controller.root;

import com.ekansrm.mlas.controller.root.dto.response.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RootController {

  @Autowired
  private BroadcastHandler broadcastHandler;

  static private Gson gson;
  static {
    gson = new GsonBuilder().setPrettyPrinting().create();
  }

  @RequestMapping(
      value = "/",
      method = RequestMethod.GET
  )
  public String root() {
    return "<h1>Machine Learning as Service</h1>";
  }

  @RequestMapping(
      value = "/about",
      method = RequestMethod.GET,
      produces = "application/json;charset=UTF-8"
  )
  public String about() {
    return gson.toJson(Response.successResponse("MLasS 是实现将机器学习模型部署到线上的一个脚手架"));
  }

  @RequestMapping(
    value = "/abcd",
    method = RequestMethod.GET,
    produces = "application/json;charset=UTF-8"
  )
  public String abcde() {
    broadcastHandler.broadcast("abcd");
    return gson.toJson(Response.successResponse(null));
  }

}
