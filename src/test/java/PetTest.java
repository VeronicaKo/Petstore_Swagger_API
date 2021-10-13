import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import pojos.*;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


public class PetTest {
    private static final RequestSpecification REQ_SPEC =
            new RequestSpecBuilder()
                    .setBaseUri("https://petstore.swagger.io/v2")
                    .setBasePath("/pet")
                    .setContentType(ContentType.JSON)
                    .build();

    @Test
    public void createPet() {
        List listTags = new ArrayList<CreateTagsItem>();
        listTags.add(CreateTagsItem.builder().id(0).name("string").build());
        List listPhoto = new ArrayList<CreatePhotoUrls>();
        listPhoto.add(CreatePhotoUrls.builder().name("string").build());

        CreatePetRequest rq = CreatePetRequest.builder()
                .name("doggie")
                .id(0)
                .createCategory(CreateCategory.builder().id(0).name("string").build())
                .tags(listTags)
//                .photoUrls(listPhoto)
                .status("available")
                .build();

        CreatePetResponse rs = given()
                .spec(REQ_SPEC)
                .body(rq)
                .when().post()
                .then().statusCode(200)
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
                .id(0)
                .createCategory(CreateCategory.builder().id(0).name("string").build())
                .tags(listTags)
//                .photoUrls(listPhoto)
                .status("available")
                .build();

        CreatePetResponse rs = given()
                .spec(REQ_SPEC)
                .body(rq)
                .when().put()
                .then().statusCode(200)
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
                .queryParam("status","sold")
//                .log().all()
                .when().get()
                .then().statusCode(200)
                .log().all()
                .extract().jsonPath().getList("id", CreatePetResponse.class);

        assertNotNull(pets);
//        assertEquals(rq.getName(), rs.getName());
    }


}


