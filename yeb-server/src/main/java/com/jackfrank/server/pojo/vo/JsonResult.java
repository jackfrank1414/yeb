package com.jackfrank.server.pojo.vo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : jackfrank
 * @version : v1.0
 * @description : 通用返回对象
 * 自定义响应数据结构
 *                 这个类是提供给门户，ios，安卓，微信商城用的
 *                 门户接受此类数据后需要使用本类的方法转换成对于的数据类型格式（类，或者list）
 *                 其他自行处理
 *                 200：表示成功
 *                 500：表示错误，错误信息在msg字段中
 *                 501：bean验证错误，不管多少个错误都以map形式返回
 *                 502：拦截器拦截到用户token出错
 *                 555：异常抛出信息
 * @createTime : 2022/8/28 18:02
 */
@Data
@NoArgsConstructor
public class JsonResult {
    /**
    * 定义jackson对象
    */
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
    * 响应业务状态
    */
    private Integer status;

    /**
    *  响应消息
    */
    private String msg;

    /**
    * 响应中的数据
    */
    private Object data;

    /**
    * 一般不使用
    */
    private String ok;

    public JsonResult(Object data){
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public JsonResult(Integer status, String msg, Object data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static JsonResult ok(){
        return new JsonResult(null);
    }

    public static JsonResult ok(Object data){
        return new JsonResult(data);
    }

    public static JsonResult ok(String msg){
        return new JsonResult(200, msg, null);
    }

    public static JsonResult ok(String msg, Object data){
        return new JsonResult(200, msg, data);
    }

    public static JsonResult build(Integer status, String msg, Object data){
        return new JsonResult(status, msg, data);
    }

    public static JsonResult errorMsg(String msg){
        return new JsonResult(500, msg, null);
    }

    public static JsonResult errorMap(Object data){
        return new JsonResult(501, "error", data);
    }

    public static JsonResult errorTokenMsg(String msg){
        return new JsonResult(502, msg, null);
    }

    public static JsonResult errorException(String msg){
        return new JsonResult(555, msg, null);
    }

    /**
    * @Description: 将json结果集转化为JsonResult对象,需要转换的对象是一个类
    * @Param: [jsonData, clazz]
    * @return: com.jackfrank.server.pojo.vo.JsonResult
    * @Author: jackfrank
    * @Date: 2022/8/28 18:38
    */
    public static JsonResult formatToPojo(String jsonData, Class<?> clazz){
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, JsonResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
    * @Description: 没有object对象的转化
    * @Param: [json]
    * @return: com.jackfrank.server.pojo.vo.JsonResult
    * @Author: jackfrank
    * @Date: 2022/8/28 18:39
    */
    public static JsonResult format(String json){
        try {
            return MAPPER.readValue(json, JsonResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
    * @Description: Object是集合转化,需要转换的对象是一个list
    * @Param: [jsonData, clazz]
    * @return: com.jackfrank.server.pojo.vo.JsonResult
    * @Author: jackfrank
    * @Date: 2022/8/28 18:39
    */
    public static JsonResult formatToList(String jsonData, Class<?> clazz){
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }
}
