package pojos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePetRequest{
	private String name;
	private int id;
	private CreateCategory createCategory;
	private List<CreateTagsItem> tags;
	private List<CreatePhotoUrls> photoUrls;
	private String status;
}