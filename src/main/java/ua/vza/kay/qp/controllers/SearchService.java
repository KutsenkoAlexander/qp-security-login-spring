package ua.vza.kay.qp.controllers;

import ua.vza.kay.qp.crud.impls.*;
import ua.vza.kay.qp.entity.Product;
import ua.vza.kay.qp.entity.ProductIncome;
import ua.vza.kay.qp.entity.SprProductName;
import ua.vza.kay.qp.utils.UnixTimeConvert;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by kycenko on 10.08.15.
 */
@Path("search")
@RequestScoped
public class SearchService {

    @Inject private SprProductNameCrud spnc;
    @Inject private SprProductTypeCrud sptc;
    @Inject private ProductCrud pc;
    @Inject private ProductIncomeCrud pic;
    @Inject private SprConsumerCrud scc;
    @Inject private ProductIncomeCrud productIncomeCrud;

    @Path("like_search")
    @GET
    @Produces("application/json")
    public JsonArray getProductLikeName(@QueryParam("name") String name) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        if (!spnc.getByLike(name).isEmpty()) {
            for (SprProductName productName : spnc.getByLike(name)){
                if(!pc.getByNameId(productName.getSprNameId()).isEmpty()){
                    builder.add(Json.createObjectBuilder()
                            .add("likeId", productName.getSprNameId())
                            .add("likeName", productName.getName()));
                }
            }
        }
        return builder.build();
    }

    @Path("do_search")
    @GET
    @Produces("application/json")
    public JsonArray doSearch(@QueryParam("page") Integer page,
                              @QueryParam("idProduct") Integer idProduct,
                              @QueryParam("nameProduct") String nameProduct,
                              @QueryParam("rezultIncomeProduct") Boolean rezultIncomeProduct,
                              @QueryParam("fromPeriod") String fromPeriod,
                              @QueryParam("toPeriod") String toPeriod) throws ParseException {
        String idDoc = "";
        String consumer = "";
        String idName = "";
        String idType = "";
        String lot = "";
        String lastModifyDate = "";
        String controlResult = "";

        ProductIncome productIncome = new ProductIncome();
        JsonArrayBuilder builder = Json.createArrayBuilder();

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Kyiv"));
        //поиск по заданному поисковому запросу без параметров поиска
        if(idProduct != 0 && nameProduct != null && rezultIncomeProduct == false && fromPeriod.length() == 0 && toPeriod.length() == 0) {
            //set json to responce
            for (Product prod : pc.getByNameId(idProduct)) {
                pc.refrash(prod.getId());
                if (prod.getNameId() == null) idName = "";
                else idName = spnc.getById(prod.getNameId()).getName();
                if (prod.getTypeId() == null) idType = "";
                else idType = sptc.getById(prod.getTypeId()).getName();
                if (prod.getLot() == null) lot = "";
                else lot = prod.getLot();
                if(null != prod.getProductIncomesById()){
                    ProductIncome income = prod.getProductIncomesById();
                    lastModifyDate = UnixTimeConvert.unixTimeToData(income.getDateLastModify());
                    if (null == income.getControl()) controlResult = "null";
                    else {
                        switch (income.getControl()) {
                            case 1:
                                controlResult = "Пройден";
                                break;
                            case 2:
                                controlResult = "Не пройден";
                                break;
                            case 999:
                                controlResult = "Новая";
                                break;
                            default:
                                controlResult = "Новая";
                        }
                    }
                }
                else{
                    System.out.println("@@@@@@@@@@@@@@@@@ NULL @@@@@@@@@@@@@@");
                    for(ProductIncome income : pic.getByProductId(prod.getId())) {
                        lastModifyDate = UnixTimeConvert.unixTimeToData(income.getDateLastModify());
                        if (null == income.getControl()) controlResult = "null";
                        else {
                            switch (income.getControl()) {
                                case 1:
                                    controlResult = "Пройден";
                                    break;
                                case 2:
                                    controlResult = "Не пройден";
                                    break;
                                case 999:
                                    controlResult = "Новая";
                                    break;
                                default:
                                    controlResult = "Новая";
                            }
                        }
                    }
                }
                builder.add(Json.createObjectBuilder()
                        .add("Name", idName)
                        .add("Type", idType)
                        .add("Lot", lot)
                        .add("DateLastModify", lastModifyDate)
                        .add("ControlResult", controlResult)
                        .add("incomeId", productIncome.getIdproductIncome()));
            }
        } else if(idProduct != 0 && nameProduct != null && rezultIncomeProduct == false && fromPeriod.length() == 0 && toPeriod.length() == 0){
            //поиск без запроса с параметрами поиска: контроль не пройден
            for(ProductIncome productIncome1 : pic.getNotControlProduct(page)){
                if(productIncome1.getProductByProductId().getNameId() == null) idName = "";
                else idName = spnc.getById(productIncome1.getProductByProductId().getNameId()).getName();
                if (productIncome1.getProductByProductId().getLot() == null) lot = "";
                else lot = productIncome1.getProductByProductId().getLot();
                lastModifyDate = UnixTimeConvert.unixTimeToData(productIncome1.getDateLastModify());
                if (null == productIncome1.getControl()) controlResult = "null";
                else {
                    switch (productIncome1.getControl()) {
                        case 1:
                            controlResult = "Пройден";
                            break;
                        case 2:
                            controlResult = "Не пройден";
                            break;
                        case 999:
                            controlResult = "Новая";
                            break;
                        default:
                            controlResult = "Новая";
                    }
                }
            }
            builder.add(Json.createObjectBuilder()
                    .add("Name", idName)
                    .add("Lot", lot)
                    .add("DateLastModify", lastModifyDate)
                    .add("ControlResult", controlResult)
                    .add("incomeId", productIncome.getIdproductIncome()));
        } else {
//          //Просто нажата кнопка "Поиск" без параметров и поискового запроса
            for (Product prod : pc.getAllLimit(page)) {
                pc.refrash(prod.getId());
                if (prod.getNameId() == null) idName = "";
                else idName = spnc.getById(prod.getNameId()).getName();
                if (prod.getTypeId() == null) idType = "";
                else idType = sptc.getById(prod.getTypeId()).getName();
                if (prod.getLot() == null) lot = "";
                else lot = prod.getLot();
                if(null != prod.getProductIncomesById()){
                    ProductIncome income = prod.getProductIncomesById();
                    lastModifyDate = UnixTimeConvert.unixTimeToData(income.getDateLastModify());
                    if (null == income.getControl()) controlResult = "null";
                    else {
                        switch (income.getControl()) {
                            case 1:
                                controlResult = "Пройден";
                                break;
                            case 2:
                                controlResult = "Не пройден";
                                break;
                            case 999:
                                controlResult = "Новая";
                                break;
                            default:
                                controlResult = "Новая";
                        }
                    }
                }
                else{
                    System.out.println("@@@@@@@@@@@@@@@@@ NULL @@@@@@@@@@@@@@");
                    for(ProductIncome income : pic.getByProductId(prod.getId())) {
                        lastModifyDate = UnixTimeConvert.unixTimeToData(income.getDateLastModify());
                        if (null == income.getControl()) controlResult = "null";
                        else {
                            switch (income.getControl()) {
                                case 1:
                                    controlResult = "Пройден";
                                    break;
                                case 2:
                                    controlResult = "Не пройден";
                                    break;
                                case 999:
                                    controlResult = "Новая";
                                    break;
                                default:
                                    controlResult = "Новая";
                            }
                        }
                    }
                }
                builder.add(Json.createObjectBuilder()
                        .add("Name", idName)
                        .add("Type", idType)
                        .add("Lot", lot)
                        .add("DateLastModify", lastModifyDate)
                        .add("ControlResult", controlResult)
                        .add("incomeId", productIncome.getIdproductIncome()));
            }
        }
        return builder.build();
    }

}
