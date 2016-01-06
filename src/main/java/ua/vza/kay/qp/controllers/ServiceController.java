package ua.vza.kay.qp.controllers;

import ua.vza.kay.qp.crud.impls.*;
import ua.vza.kay.qp.entity.*;
import ua.vza.kay.qp.utils.UnixTimeConvert;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

/**
 * Created by kycenko on 16.06.15.
 */
@Path("service")
@RequestScoped
public class ServiceController {

    @Inject private SprProductNameCrud spnc;
    @Inject private SprProductTypeCrud sptc;
    @Inject private SprMagazineCrud mc;
    @Inject private SprStandartTypeCrud sstc;
    @Inject private SprStandartNameCrud ssc;
    @Inject private SprConsumerCrud scc;
    @Inject private SprMeasureCrud smc;
    @Inject private SprLocationCrud slc;
    @Inject private ProductCrud pc;
    @Inject private ProductIncomeCrud pic;
    @Inject private DocumentIncomeproductCrud dc;
    @Inject private DocumentProductCrud dpc;

    @Path("join_product")
    @GET
    @Produces("application/json")
    public JsonArray getAllEditProduct(@QueryParam("page") Integer page) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        String idName = "";
        String idType = "";
        String lot = "";
        String lastModifyDate = "";
        String controlResult = "";
        for (Product prod : pc.getAllLimit(page)) {
            pc.refrasheProduct(prod.getId());
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
                            .add("ProductId", prod.getId())
            );
        }
        return builder.build();
    }

    @Path("sort_magazine")
    @GET
    @Produces("application/json")
    public JsonArray sortMagazine(@QueryParam("page") Integer page, @QueryParam("magazine") Integer magazine) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        String idName = "";
        String lot = "";
        String controlResult = "";
        for (ProductIncome productIncome : pic.getByMagazineIdLimit(page, magazine)) {
            pic.refrash(productIncome.getIdproductIncome());
            if (pc.getById(productIncome.getProductId()).getNameId() == null) idName = "";
            else idName = spnc.getById(pc.getById(productIncome.getProductId()).getNameId()).getName();
            if (pc.getById(productIncome.getProductId()).getLot() == null) lot = "";
            else lot = pc.getById(productIncome.getProductId()).getLot();
            if (null == productIncome.getControl()) controlResult = "null";
            else {
                switch (productIncome.getControl()) {
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
            builder.add(Json.createObjectBuilder()
                    .add("Name", idName)
                    .add("Lot", lot)
                    .add("DateLastModify", UnixTimeConvert.unixTimeToData(productIncome.getDateLastModify()))
                    .add("ControlResult", controlResult)
                    .add("ProductId", productIncome.getProductId())
                    .add("IncomeId", productIncome.getIdproductIncome()));
        }
        return builder.build();
    }

    @Path("get_product_by_id")
    @GET
    @Produces("application/json")
    public JsonArray getProductById(@QueryParam("id") Integer id) {
        Product prod = pc.getById(id);
        pc.refrasheProduct(prod.getId());

        JsonArrayBuilder builder = Json.createArrayBuilder();
        JsonArrayBuilder builderMagazines = Json.createArrayBuilder();
        JsonArrayBuilder builderMeasures = Json.createArrayBuilder();
        JsonArrayBuilder builderLocation = Json.createArrayBuilder();
        JsonArrayBuilder builderAttachFiles = Json.createArrayBuilder();
        JsonArrayBuilder builderAttachDocument = Json.createArrayBuilder();

        String incomeId = null;
        //String magazine = null;
        String magazineId = null;
        String name = null;
        String idName = null;
        String stdName = null;
        String stdNameId = null;
        String type = null;
        String idType = null;
        String stdType = null;
        String stdTypeId = null;
        String waranty = null;
        String produceDate = null;
        String dataPresenter = null;
        String lot = null;
        String attachDocumentId = null;
        String attachDocumentName = null;
        String attachDocument = null;
        String consumer = null;
        String consumerId = null;
        String countProduct = null;
        String measure = null;
        String fioPresenter = null;
        String location = null;
        String fioLastModify = null;
        String dataLastModify = null;
        String fioControler = null;
        String arrangaments = null;
        String note = null;
        String attachFileId = null;
        String attachFileName = null;
        String attachFileLink = null;
        Integer control = null;
        //get name product
        if (prod.getNameId() == null) name = "";
        else name = spnc.getById(prod.getNameId()).getName();
        //get name id product
        if (prod.getNameId() == null) idName = "";
        else idName = String.valueOf(prod.getNameId());
        //get standart name product
        if (prod.getStdNameId() == null) stdName = "";
        else stdName = ssc.getById(prod.getStdNameId()).getName();
        //get standart name id product
        if (prod.getStdNameId() == null) stdNameId = "";
        else stdNameId = String.valueOf(prod.getStdNameId());
        //get name type product
        if (prod.getTypeId() == null) type = "";
        else type = sptc.getById(prod.getTypeId()).getName();
        //get id type product
        if (prod.getTypeId() == null) idType = "";
        else idType = String.valueOf(prod.getTypeId());
        //get standart type product
        if (prod.getStdTypeId() == null) stdType = "";
        else stdType = ssc.getById(prod.getStdTypeId()).getName();
        //get standart type id product
        if (prod.getStdTypeId() == null) stdTypeId = "";
        else stdTypeId = String.valueOf(prod.getStdTypeId());
        //get product waranty
        if (prod.getWaranty() == null) waranty = "";
        else waranty = String.valueOf(prod.getWaranty());
        //get produce date
        if (prod.getProduceDate() == null) produceDate = "";
        else produceDate = UnixTimeConvert.unixTimeToData(prod.getProduceDate());
        //get lot produce
        if (prod.getLot() == null) lot = "";
        else lot = prod.getLot();
        //get consumer
        if (prod.getConsumerId() == null) consumer = "";
        else consumer = scc.getById(prod.getConsumerId()).getName();
        //get consumer id
        if (prod.getConsumerId() == null) consumerId = "";
        else consumerId = String.valueOf(prod.getConsumerId());
        //get attachment accompanying document
        for (DocumentProduct documentProduct : prod.getDocumentProductsById()){
            if (documentProduct.getIdDocProduct() == 0) attachDocumentId = "";
            else attachDocumentId = String.valueOf(documentProduct.getIdDocProduct());
            if (documentProduct.getName() == null) attachDocumentName = "";
            else attachDocumentName = documentProduct.getName();
            if (null == documentProduct.getLink()) attachDocument = null;
            else attachDocument = documentProduct.getLink();
            builderAttachDocument.add(Json.createObjectBuilder()
                    .add("DocumentId", attachDocumentId)
                    .add("DocumentName", attachDocumentName)
                    .add("FileDocument", String.valueOf(attachDocument)));
        }
        //get income product
        ProductIncome productIncome = prod.getProductIncomesById();
        //get income id //be able to NULL
        if(null != prod.getProductIncomesById()){
            if (productIncome.getIdproductIncome() == 0) incomeId = "";
            else incomeId = String.valueOf(productIncome.getIdproductIncome());
            //get magazine id
            if (productIncome.getMagazineId() == 0) magazineId = "";
            else {
                magazineId = String.valueOf(productIncome.getMagazineId());
                //get magazine
                for (SprMagazine mag : mc.getAll()) {
                    builderMagazines.add(Json.createObjectBuilder()
                            .add("MagId", mag.getMagazineId())
                            .add("MagName", mag.getName()));
                }
            }
            //get count produce
            if (productIncome.getCountProduct() == 0) countProduct = "";
            else countProduct = String.valueOf(productIncome.getCountProduct());
            //get measure
            if (productIncome.getMeasureId() == 0) measure = "";
            else {
                measure = String.valueOf(productIncome.getMeasureId());
                //get measures
                for (SprMeasure measures : smc.getAll()) {
                    builderMeasures.add(Json.createObjectBuilder()
                            .add("MeasureId", measures.getMeasureId())
                            .add("MeasureName", measures.getName()));
                }
            }
            //get date presenter
            if (productIncome.getDatePresenter() == 0) dataPresenter = "";
            else dataPresenter = UnixTimeConvert.unixTimeToData(productIncome.getDatePresenter());
            //get fio presenter
            if (productIncome.getFioPresenter() == null) fioPresenter = "";
            else fioPresenter = productIncome.getFioPresenter();
            //get location
            if (productIncome.getLocationId() == 0) location = "";
            else {
                location = String.valueOf(productIncome.getLocationId());
                //get location
                for (SprLocation loc : slc.getAll()) {
                    builderLocation.add(Json.createObjectBuilder()
                            .add("LocationId", loc.getLocationId())
                            .add("LocationName", loc.getName()));
                }
            }
            //get fio last modify
            if (productIncome.getFioLastModify() == null) fioLastModify = "";
            else fioLastModify = productIncome.getFioLastModify();
            //get date last modify
            if (productIncome.getDateLastModify() == 0) dataLastModify = "";
            else dataLastModify = UnixTimeConvert.unixTimeToData(productIncome.getDateLastModify());
            //get fio controler
            if (productIncome.getFioControler() == null) fioControler = "";
            else fioControler = productIncome.getFioControler();
            //get result control
            if (productIncome.getControl() == null) control = 0;
            else control = productIncome.getControl();
            //get arrangaments
            if (productIncome.getArrangements() == null) arrangaments = "";
            else arrangaments = productIncome.getArrangements();
            //get note
            if (productIncome.getNote() == null) note = "";
            else note = productIncome.getNote();
            //get attach files
            for (DocumentIncomeproduct document : productIncome.getDocumentIncomeproductsByIdproductIncome()) {
                //get attach id files
                if (document.getIncomeId() == 0) attachFileId = "";
                else attachFileId = String.valueOf(document.getDocumentId());
                //get attach name file
                if (document.getName() == null) attachFileName = "";
                else attachFileName = document.getName();
                //get attach file link
                if (document.getLink() == null) attachFileLink = "";
                else attachFileLink = document.getLink();
                builderAttachFiles.add(Json.createObjectBuilder()
                        .add("FileId", attachFileId)
                        .add("FileName", attachFileName)
                        .add("FileLink", attachFileLink));
            }
        } else {
            for (ProductIncome income : pic.getByProductId(prod.getId())) {
                if (income.getIdproductIncome() == 0) incomeId = "";
                else incomeId = String.valueOf(income.getIdproductIncome());
                //get magazine id
                if (income.getMagazineId() == 0) magazineId = "";
                else {
                    magazineId = String.valueOf(income.getMagazineId());
                    //get magazine
                    for (SprMagazine mag : mc.getAll()) {
                        builderMagazines.add(Json.createObjectBuilder()
                                .add("MagId", mag.getMagazineId())
                                .add("MagName", mag.getName()));
                    }
                }
                //get count produce
                if (income.getCountProduct() == 0) countProduct = "";
                else countProduct = String.valueOf(income.getCountProduct());
                //get measure
                if (income.getMeasureId() == 0) measure = "";
                else {
                    measure = String.valueOf(income.getMeasureId());
                    //get measures
                    for (SprMeasure measures : smc.getAll()) {
                        builderMeasures.add(Json.createObjectBuilder()
                                .add("MeasureId", measures.getMeasureId())
                                .add("MeasureName", measures.getName()));
                    }
                }
                //get date presenter
                if (income.getDatePresenter() == 0) dataPresenter = "";
                else dataPresenter = UnixTimeConvert.unixTimeToData(income.getDatePresenter());
                //get fio presenter
                if (income.getFioPresenter() == null) fioPresenter = "";
                else fioPresenter = income.getFioPresenter();
                //get location
                if (income.getLocationId() == 0) location = "";
                else {
                    location = String.valueOf(income.getLocationId());
                    //get location
                    for (SprLocation loc : slc.getAll()) {
                        builderLocation.add(Json.createObjectBuilder()
                                .add("LocationId", loc.getLocationId())
                                .add("LocationName", loc.getName()));
                    }
                }
                //get fio last modify
                if (income.getFioLastModify() == null) fioLastModify = "";
                else fioLastModify = income.getFioLastModify();
                //get date last modify
                if (income.getDateLastModify() == 0) dataLastModify = "";
                else dataLastModify = UnixTimeConvert.unixTimeToData(income.getDateLastModify());
                //get fio controler
                if (income.getFioControler() == null) fioControler = "";
                else fioControler = income.getFioControler();
                //get result control
                if (income.getControl() == null) control = 0;
                else control = income.getControl();
                //get arrangaments
                if (income.getArrangements() == null) arrangaments = "";
                else arrangaments = income.getArrangements();
                //get note
                if (income.getNote() == null) note = "";
                else note = income.getNote();
                //get attach files
                for (DocumentIncomeproduct document : income.getDocumentIncomeproductsByIdproductIncome()) {
                    //get attach id files
                    if (document.getIncomeId() == 0) attachFileId = "";
                    else attachFileId = String.valueOf(document.getDocumentId());
                    //get attach name file
                    if (document.getName() == null) attachFileName = "";
                    else attachFileName = document.getName();
                    //get attach file link
                    if (document.getLink() == null) attachFileLink = "";
                    else attachFileLink = document.getLink();
                    builderAttachFiles.add(Json.createObjectBuilder()
                            .add("FileId", attachFileId)
                            .add("FileName", attachFileName)
                            .add("FileLink", attachFileLink));
                }
            }
        }
        builder.add(Json.createObjectBuilder()
                        .add("ProductId", prod.getId())
                        .add("IncomeId", incomeId)
                        .add("Magazines", builderMagazines)
                        .add("MagazineId", magazineId)
                        .add("Name", name)
                        .add("IdName", idName)
                        .add("StdName", stdName)
                        .add("StdNameId", stdNameId)
                        .add("Type", type)
                        .add("IdType", idType)
                        .add("StdType", stdType)
                        .add("StdTypeId", stdTypeId)
                        .add("Waranty", waranty)
                        .add("ProduceDate", produceDate)
                        .add("DataPresenter", dataPresenter)
                        .add("Lot", lot)
                        .add("Consumer", consumer)
                        .add("ConsumerId", consumerId)
                        .add("CountProduct", countProduct)
                        .add("Measures", builderMeasures)
                        .add("Measure", measure)
                        .add("FioPresenter", fioPresenter)
                        .add("Locations", builderLocation)
                        .add("Location", location)
                        .add("FioLastModify", fioLastModify)
                        .add("DateLastModify", dataLastModify)
                        .add("FioControler", fioControler)
                        .add("ResultControl", control)
                        .add("Arrangaments", arrangaments)
                        .add("AttachFiles", builderAttachFiles)
                        .add("AttachDocuments", builderAttachDocument)
                        .add("Note", note)
        );
        return builder.build();
    }

    @Path("all_magazine")
    @GET
    @Produces("application/json")
    public JsonArray getAllMagazine() {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (SprMagazine magazine : mc.getAll()) {
            builder.add(Json.createObjectBuilder()
                    .add("MagazineId", magazine.getMagazineId())
                    .add("MagazineName", magazine.getName()));
        }
        return builder.build();
    }

    @Path("all_std_type")
    @GET
    @Produces("application/json")
    public JsonArray getAllStdType() {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (SprStandartType stdType : sstc.getAll()) {
            builder.add(Json.createObjectBuilder()
                    .add("stdId", stdType.getSprStdTypeId())
                    .add("stdName", stdType.getName()));
        }
        return builder.build();
    }

    @Path("all_measure")
    @GET
    @Produces("application/json")
    public JsonArray getAllMeasure() {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (SprMeasure measure : smc.getAll()) {
            builder.add(Json.createObjectBuilder()
                    .add("MeasureId", measure.getMeasureId())
                    .add("MeasureName", measure.getName()));
        }
        return builder.build();
    }

    @Path("location")
    @GET
    @Produces("application/json")
    public JsonArray getLocation() {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (SprLocation location : slc.getAll()) {
            builder.add(Json.createObjectBuilder()
                    .add("LocationId", location.getLocationId())
                    .add("LocationName", location.getName()));
        }
        return builder.build();
    }

    @Path("product_like_name")
    @GET
    @Produces("application/json")
    public JsonArray getProductLikeName(@QueryParam("name") String name) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (SprProductName productName : spnc.getByLike(name)) {
            builder.add(Json.createObjectBuilder()
                    .add("likeId", productName.getSprNameId())
                    .add("likeName", productName.getName()));
        }
        return builder.build();
    }

    @Path("product_like_type")
    @GET
    @Produces("application/json")
    public JsonArray getProductLikeType(@QueryParam("name") String name) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (SprProductType productType : sptc.getByLike(name)) {
            builder.add(Json.createObjectBuilder()
                    .add("likeId", productType.getSprTypeId())
                    .add("likeName", productType.getName()));
        }
        return builder.build();
    }

    @Path("standart_like")
    @GET
    @Produces("application/json")
    public JsonArray getStandartLike(@QueryParam("name") String name) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (SprStandartName productType : ssc.getByLike(name)) {
            builder.add(Json.createObjectBuilder()
                    .add("likeId", productType.getSprStdNameId())
                    .add("likeName", productType.getName()));
        }
        return builder.build();
    }

    @Path("consumer_like_name")
    @GET
    @Produces("application/json")
    public JsonArray getConsumerLikeName(@QueryParam("name") String name) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (SprConsumer consumer : scc.getByLike(name)) {
            builder.add(Json.createObjectBuilder()
                    .add("likeId", consumer.getConsumerId())
                    .add("likeName", consumer.getName()));
        }
        return builder.build();
    }

    @Path("add_consumer")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public int setConsumer(JsonObject newConsumer) {
        SprConsumer sprConsumer = new SprConsumer();
        String nameConsumer = newConsumer.getString("consumer_id");
        ArrayList<String> listConsumer = new ArrayList<String>();
        for (SprConsumer consumer : scc.getAll()) {
            listConsumer.add(consumer.getName().toUpperCase().trim());
            listConsumer.add(String.valueOf(consumer.getConsumerId()));
        }
        if (listConsumer.contains(nameConsumer.toUpperCase().trim()) == false) {
            sprConsumer.setName(nameConsumer);
            return scc.create(sprConsumer).getConsumerId();
        }
        return scc.getByName(nameConsumer).getConsumerId();
    }

    @Path("create_product")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void setProduct(JsonObject content) throws ParseException {
        Product product = new Product();
        ProductIncome productIncome = new ProductIncome();
        DocumentIncomeproduct document = new DocumentIncomeproduct();
        DocumentProduct documentProduct = new DocumentProduct();

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Kyiv"));

        if (content.getString("productId").isEmpty()) product.setId(0);
        else product.setId(Integer.valueOf(content.getString("productId")));

        if (content.getString("productIncomeId").isEmpty()) productIncome.setIdproductIncome(0);
        else productIncome.setIdproductIncome(Integer.valueOf(content.getString("productIncomeId")));

        product.setNameId(Integer.valueOf(content.getString("idProdName")));
        product.setStdNameId(Integer.valueOf(content.getString("nameStandartProdName")));
        product.setTypeId(Integer.valueOf(content.getString("idProdType")));
        product.setStdTypeId(Integer.valueOf(content.getString("nameStandartProdType")));

        if(content.getString("waranty").isEmpty()) product.setWaranty(0L);
        else product.setWaranty(dateFormat.parse(content.getString("waranty")).getTime() / 1000);

        product.setProduceDate(dateFormat.parse(content.getString("produceDate")).getTime() / 1000);
        product.setLot(content.getString("lot"));
        product.setConsumerId(Integer.valueOf(content.getString("consumerId")));

        productIncome.setMagazineId(Integer.valueOf(content.getString("idMagazine")));
        productIncome.setDatePresenter(dateFormat.parse(content.getString("datePresenter")).getTime() / 1000);
        productIncome.setCountProduct(Integer.valueOf(content.getString("countProduct")));
        productIncome.setMeasureId(Integer.valueOf(content.getString("measureId")));
        productIncome.setLocationId(Integer.valueOf(content.getString("locationId")));
        productIncome.setFioPresenter(content.getString("fioPresenter"));

        productIncome.setControl(Integer.valueOf(content.getString("resultControl")));

        String fioLastModify = content.getString("fioLastModify");
        if (fioLastModify == null) fioLastModify = "";
        productIncome.setFioLastModify(fioLastModify);

        String str1 = content.getString("dateLastModify");
        long dateLastModify;
        if (str1.isEmpty()) dateLastModify = 0;
        else dateLastModify = dateFormat.parse(str1).getTime() / 1000;
        productIncome.setDateLastModify(dateLastModify);

        String fioControler = content.getString("fioControler");
        if (fioControler == null) fioControler = "";
        productIncome.setFioControler(fioControler);

        String arrangements = content.getString("arrangements");
        if (arrangements == null) arrangements = "";
        productIncome.setArrangements(arrangements);

        String note = content.getString("note");
        if (note == null) note = "";
        productIncome.setNote(note);

        int idProductAfterInsert = pc.create(product).getId();

        productIncome.setProductId(idProductAfterInsert);

        int piId = pic.create(productIncome).getIdproductIncome();

        if (!content.getJsonArray("files").isEmpty()) {
            for(int i = 0; i < content.getJsonArray("files").size(); i++){
                JsonObject jsonObject = content.getJsonArray("files").getJsonObject(i);
                if(Integer.valueOf(jsonObject.getString("idAttach")) == 0) {
                    document.setName(String.valueOf(jsonObject.getString("name")));
                    document.setLink(String.valueOf(jsonObject.getString("link")));
                    document.setIncomeId(piId);
                    dc.create(document);
                }
            }
        }
        if (!content.getJsonArray("docs").isEmpty()) {
            for(int i = 0; i < content.getJsonArray("docs").size(); i++){
                JsonObject jsonDoc = content.getJsonArray("docs").getJsonObject(i);
                if(Integer.valueOf(jsonDoc.getString("idAttach")) == 0) {
                    documentProduct.setName(String.valueOf(jsonDoc.getString("name")));
                    documentProduct.setLink(String.valueOf(jsonDoc.getString("link")));
                    documentProduct.setProductId(idProductAfterInsert);
                    dpc.create(documentProduct);
                }
            }
        }
    }

    @Path("count_new_product")
    @GET
    @Produces("application/text")
    public Object getCountNewProduct(@QueryParam("magazine_id") int magazineId){
        return pic.getCountNewProduct(magazineId).toString();
    }

}
