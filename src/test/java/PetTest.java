import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.StringContains.containsString;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


public class PetTest {
    private static final RequestSpecification REQ_SPEC =
            new RequestSpecBuilder()
                    .setBaseUri("https://petstore.swagger.io/v2")
                    .setBasePath("/pet")
                    .setContentType(ContentType.JSON)
                    .log(LogDetail.ALL)
                    .build();

    ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectBody(containsString("success"))
            .build();

    @Test
    public void createPet() {
        List listTags = new ArrayList<CreateTagsItem>();
        listTags.add(CreateTagsItem.builder().id(0).name("string").build());
        List listPhoto = new ArrayList<CreatePhotoUrls>();
        listPhoto.add(CreatePhotoUrls.builder().name("string").build());

        CreatePetRequest rq = CreatePetRequest.builder()
                .name("doggie")
                .id(10)
                .createCategory(CreateCategory.builder().id(0).name("string").build())
                .tags(listTags)
//                .photoUrls(listPhoto)
                .status("available")
                .build();

        CreatePetResponse rs = given()
                .spec(REQ_SPEC)
                .body(rq)
                .when().post()
                .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract().as(CreatePetResponse.class);

        assertNotNull(rs);
        assertEquals(rq.getName(), rs.getName());
    }

    @Test
    public void updatePet() {
        List listTags = new ArrayList<CreateTagsItem>();
        listTags.add(CreateTagsItem.builder().id(0).name("string").build());
        List listPhoto = new ArrayList<CreatePhotoUrls>();
        listPhoto.add(CreatePhotoUrls.builder().name("string").build());

        CreatePetRequest rq = CreatePetRequest.builder()
                .name("loggie")
                .id(10)
                .createCategory(CreateCategory.builder().id(0).name("string").build())
                .tags(listTags)
//                .photoUrls(listPhoto)
                .status("available")
                .build();

        CreatePetResponse rs = given()
                .spec(REQ_SPEC)
                .body(rq)
                .when().put()
                .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract().as(CreatePetResponse.class);

        assertNotNull(rs);
        assertEquals(rq.getName(), rs.getName());
    }

    @Test
    public void findByStatusPet() {
        List<CreatePetResponse> pets =
                given()
                        .spec(REQ_SPEC)
                        .basePath("/pet/findByStatus")
                        .queryParam("status", "sold")
                        .when().get()
                        .then()
                        .assertThat().statusCode(200)
                        .extract().jsonPath().get("name");
//        .getList("photoUrls", CreatePetResponse.class);

        assertNotNull(pets);
        assert (pets.size() > 0);
    }

    @Test
    public void deletePet() {
        given()
                .spec(REQ_SPEC)
                .basePath("/pet/15435006001660")
                .when().delete()
                .then()
                .assertThat().statusCode(200);

    }

    @Test
    public void uploadImageForPet() {

        ResponsePetImage rs = given()
                .spec(REQ_SPEC)
                .contentType(ContentType.MULTIPART)
                .multiPart(new File("dog.jpg"))
                .accept("application/json")
                .basePath("/pet/90369791")
                .when().post("/uploadImage")
                .then()
                .assertThat().statusCode(200)
                .extract().as(ResponsePetImage.class);

        Assert.assertTrue(rs.getMessage().contains("File uploaded to ./dog.jpg, 85330 bytes"));


    }


}





