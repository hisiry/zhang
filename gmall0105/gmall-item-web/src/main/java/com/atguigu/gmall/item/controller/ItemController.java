package com.atguigu.gmall.item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONArray;
import com.atguigu.gmall.bean.PmsProductSaleAttr;
import com.atguigu.gmall.bean.PmsSkuInfo;
import com.atguigu.gmall.bean.PmsSkuSaleAttrValue;
import com.atguigu.gmall.service.SkuService;
import com.atguigu.gmall.service.SpuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description ItemController
 * @Author zhonghua.zhang
 * @Date 2019/11/23 14:22
 * @Param
 * @return
 **/
@Controller
public class ItemController {
    @Reference
    SkuService skuService;

    @Reference
    SpuService spuService;


    @RequestMapping("/index")
    public String index(ModelMap modelMap){

        List<String> list=new ArrayList<>();
        for (int i=0;i<5;i++){
            list.add("循环数据"+i);
         }
        modelMap.put("hello","helloword!");
        modelMap.put("list",list);
        modelMap.put("check",1);
        return "index";
    }


    @RequestMapping("{skuId}.html")
    public String item(@PathVariable String skuId,ModelMap map){

        PmsSkuInfo skuInfo=skuService.getSkuById(skuId);
        //sku对象
        map.put("skuInfo",skuInfo);

        List<PmsProductSaleAttr> pmsProductSaleAttrs=spuService.spuSaleAttrListCheckBySku(skuInfo.getProductId(),skuInfo.getId());
        //销售属性列表
        map.put("spuSaleAttrListCheckBySku",pmsProductSaleAttrs);
        //查询当前sku的spu的其他sku的集合的hash表
        Map<String, String> skuSaleAttrHash = new HashMap<>();
        List<PmsSkuInfo> pmsSkuInfos=skuService.getSkuSaleAttrValueListBySpu(skuInfo.getProductId());
        for (PmsSkuInfo pmsSkuInfo : pmsSkuInfos) {
            String k="";
            String v=pmsSkuInfo.getId();
            List<PmsSkuSaleAttrValue> skuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
            for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
                k+=pmsSkuSaleAttrValue.getSaleAttrValueId()+"|";
            }
            skuSaleAttrHash.put(k,v);
        }
        //将sku的销售属性hash表放到页面
        String skuSaleAttrHashJsonStr = JSONArray.toJSONString(skuSaleAttrHash);
        map.put("skuSaleAttrHashJsonStr",skuSaleAttrHashJsonStr);
        return "item";
    }




}
