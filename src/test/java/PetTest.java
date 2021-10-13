import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import pojos.*;

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
        CreatePetRequest rq = CreatePetRequest.builder()
                .name("doggie")
                .id(0)
                .createCategory(CreateCategory.builder().id(0).name("string").build())
//                .tags((List<CreateTagsItem>) CreateTagsItem.builder().id(0).name("string").build())
//                .photoUrls((List<CreatePhotoUrls>) CreatePhotoUrls.builder().name("string").build())
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
}
